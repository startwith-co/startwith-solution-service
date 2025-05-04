package startwithco.solutionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startwithco.solutionservice.exception.server.ServerErrorResult;
import startwithco.solutionservice.exception.server.ServerException;
import startwithco.solutionservice.domain.SolutionEntity;
import startwithco.solutionservice.repository.SolutionRepository;
import startwithco.solutionservice.service.mapper.SolutionServiceMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static startwithco.solutionservice.service.dto.ResponseDto.*;
import static startwithco.solutionservice.topic.ConsumerTopic.TOSS_PAYMENT_APPROVAL_TOPIC;
import static startwithco.solutionservice.topic.ProducerTopic.TOSS_PAYMENT_QUERY_TOPIC;

@Service
@RequiredArgsConstructor
public class SolutionService {
    private final SolutionRepository repository;
    private final SolutionServiceMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public PaymentResponseDto getTossPaymentParam(Long solutionSeq, Long buyerSeq, Long sellerSeq) {
        SolutionEntity result = repository.findWithLockBySolutionSeqForWaiting(solutionSeq);
        String orderId = UUID.randomUUID().toString();

        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("solutionSeq", result.getSolutionSeq());
            payload.put("buyerSeq", buyerSeq);
            payload.put("sellerSeq", sellerSeq);
            payload.put("amount", result.getAmount());
            payload.put("orderId", orderId);
            payload.put("orderName", result.getSolutionName());
            String json = objectMapper.writeValueAsString(payload);

            kafkaTemplate.send(TOSS_PAYMENT_QUERY_TOPIC, json);
        } catch (Exception e) {
            throw new ServerException(ServerErrorResult.INTERNAL_SERVER_EXCEPTION);
        }

        return mapper.toPaymentResponseDto(result.getAmount(), orderId, result.getSolutionName());
    }

    @Transactional
    @KafkaListener(topics = TOSS_PAYMENT_APPROVAL_TOPIC, groupId = "group-01")
    public void tossPaymentApproval(String event) {
        try {
            SolutionEntity result = repository.findWithLockBySolutionSeqForWaiting(Long.parseLong(event));
            result.upSellCount();

            repository.saveAndFlush(result);
        } catch (Exception e) {
            /*
             * TODO
             * SellCount가 제대로 업데이트 되지 않으면 어떻게 처리?
             * 이미 TossPayment는 승인이 났을 것으로 예상
             */

            throw new ServerException(ServerErrorResult.INTERNAL_SERVER_EXCEPTION);
        }
    }
}

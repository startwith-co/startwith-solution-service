package startwithco.solutionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startwithco.solutionservice.exception.notFound.NotFoundErrorResult;
import startwithco.solutionservice.exception.notFound.NotFoundException;
import startwithco.solutionservice.exception.server.ServerErrorResult;
import startwithco.solutionservice.exception.server.ServerException;
import startwithco.solutionservice.domain.SolutionEntity;
import startwithco.solutionservice.repository.SolutionRepository;
import startwithco.solutionservice.service.mapper.SolutionServiceMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static startwithco.solutionservice.topic.producer.ProducerTopic.SOLUTION_PAYMENT_TOPIC;
import static startwithco.solutionservice.service.dto.ResponseDto.*;

@Service
@RequiredArgsConstructor
public class SolutionService {
    private final SolutionRepository repository;
    private final SolutionServiceMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public PaymentResponseDto getTossPaymentParam(Long solutionSeq) {
        SolutionEntity solutionEntity = repository.findWithLockBySolutionSeqForWaiting(solutionSeq);
        solutionEntity.occupy();
        SolutionEntity result = repository.saveAndFlush(solutionEntity);

        String orderId = UUID.randomUUID().toString();

        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("amount", result.getAmount());
            payload.put("orderId", orderId);
            payload.put("orderName", result.getSolutionName());

            String json = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(SOLUTION_PAYMENT_TOPIC, json);
        } catch (Exception e) {
            throw new ServerException(ServerErrorResult.INTERNAL_SERVER_EXCEPTION);
        }

        return mapper.toPaymentResponseDto(result.getAmount(), orderId, result.getSolutionName());
    }
}

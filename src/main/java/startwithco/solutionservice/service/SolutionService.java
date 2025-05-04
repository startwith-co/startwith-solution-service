package startwithco.solutionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startwithco.solutionservice.exception.server.ServerErrorResult;
import startwithco.solutionservice.exception.server.ServerException;
import startwithco.solutionservice.domain.SolutionEntity;
import startwithco.solutionservice.repository.SolutionRepository;
import startwithco.solutionservice.service.mapper.SolutionServiceMapper;

import static startwithco.solutionservice.service.dto.ResponseDto.*;
import static startwithco.solutionservice.topic.ConsumerTopic.TOSS_PAYMENT_APPROVAL_TOPIC;

@Service
@RequiredArgsConstructor
public class SolutionService {
    private final SolutionRepository repository;
    private final SolutionServiceMapper mapper;

    @Transactional
    public SolutionResponseDto getSolution(Long solutionSeq) {
        SolutionEntity solutionEntity = repository.findBySolutionSeq(solutionSeq);

        return mapper.toPaymentResponseDto(solutionEntity);
    }

    @Transactional
    @KafkaListener(topics = TOSS_PAYMENT_APPROVAL_TOPIC, groupId = "group-01")
    public void tossPaymentApproval(String event) {
        /*
         * TODO
         *  테스트 필요
         * */
        SolutionEntity result = repository.findWithLockBySolutionSeqForWaiting(Long.parseLong(event));

        try {
            result.upSellCount();

            repository.saveAndFlush(result);
        } catch (Exception e) {
            /*
             * TODO
             *  SellCount가 제대로 업데이트 되지 않으면 어떻게 처리?
             *  이미 TossPayment는 승인이 났을 것으로 예상
             */

            throw new ServerException(ServerErrorResult.INTERNAL_SERVER_EXCEPTION);
        }
    }
}

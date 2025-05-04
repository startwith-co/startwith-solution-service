package startwithco.solutionservice.repository;

import startwithco.solutionservice.domain.SolutionEntity;

public interface SolutionRepository {
    SolutionEntity findWithLockBySolutionSeqForWaiting(Long solutionSeq);

    SolutionEntity saveAndFlush(SolutionEntity entity);

    SolutionEntity findBySolutionSeq(Long solutionSeq);
}

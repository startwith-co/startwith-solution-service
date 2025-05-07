package startwithco.solutionservice.solution.repository;

import startwithco.solutionservice.solution.domain.SolutionEntity;

public interface SolutionRepository {
    SolutionEntity findWithLockBySolutionSeqForWaiting(Long solutionSeq);

    SolutionEntity saveAndFlush(SolutionEntity entity);

    SolutionEntity findBySolutionSeq(Long solutionSeq);
}

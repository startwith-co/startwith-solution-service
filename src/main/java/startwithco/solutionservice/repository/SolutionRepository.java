package startwithco.solutionservice.repository;

import startwithco.solutionservice.domain.SolutionEntity;

import java.util.Optional;

public interface SolutionRepository {
    Optional<SolutionEntity> findWithLockBySolutionSeqForWaiting(Long solutionSeq);

    SolutionEntity saveAndFlush(SolutionEntity entity);
}

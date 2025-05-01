package startwithco.solutionservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import startwithco.solutionservice.domain.SolutionEntity;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SolutionRepositoryImpl implements SolutionRepository {
    private final SolutionJpaRepository repository;

    @Override
    public Optional<SolutionEntity> findWithLockBySolutionSeqForWaiting(Long solutionSeq) {
        return repository.findWithLockBySolutionSeqForWaiting(solutionSeq);
    }

    @Override
    public SolutionEntity saveAndFlush(SolutionEntity entity) {
        return repository.saveAndFlush(entity);
    }
}

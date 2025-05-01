package startwithco.solutionservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import startwithco.solutionservice.domain.SolutionEntity;
import startwithco.solutionservice.exception.notFound.NotFoundErrorResult;
import startwithco.solutionservice.exception.notFound.NotFoundException;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SolutionRepositoryImpl implements SolutionRepository {
    private final SolutionJpaRepository repository;

    @Override
    public SolutionEntity findWithLockBySolutionSeqForWaiting(Long solutionSeq) {
        return repository.findWithLockBySolutionSeqForWaiting(solutionSeq)
                .orElseThrow(() -> new NotFoundException(NotFoundErrorResult.SOLUTION_NOT_FOUND_EXCEPTION));
    }

    @Override
    public SolutionEntity saveAndFlush(SolutionEntity entity) {
        return repository.saveAndFlush(entity);
    }
}

package startwithco.solutionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startwithco.solutionservice.domain.SolutionEntity;
import startwithco.solutionservice.repository.SolutionEntityJpaRepository;

@Service
@RequiredArgsConstructor
public class SolutionService {
    private final SolutionEntityJpaRepository solutionEntityJpaRepository;

    @Transactional
    public void findLock(Long solutionSeq) {
        SolutionEntity solutionEntity = solutionEntityJpaRepository.findWithLockBySolutionSeqForWaiting(solutionSeq)
                .orElseThrow(() -> new IllegalArgumentException("Solution sequence " + solutionSeq + " not found"));

        solutionEntity.occupy();

        solutionEntityJpaRepository.saveAndFlush(solutionEntity);
    }
}

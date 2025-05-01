package startwithco.solutionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import startwithco.solutionservice.domain.SolutionEntity;

import java.util.Optional;

@Repository
public interface SolutionEntityJpaRepository extends JpaRepository<SolutionEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from SolutionEntity s where s.solutionSeq = :solutionSeq and s.status = 'WAITING_OCCUPY'")
    Optional<SolutionEntity> findWithLockBySolutionSeqForWaiting(@Param("solutionSeq") Long solutionSeq);
}

package startwithco.solutionservice.service.test;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import startwithco.solutionservice.domain.CLOUD;
import startwithco.solutionservice.domain.FIELD;
import startwithco.solutionservice.domain.STATUS;
import startwithco.solutionservice.solution.domain.SolutionEntity;
import startwithco.solutionservice.repository.SolutionEntityJpaRepository;
import startwithco.solutionservice.solution.service.SolutionService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolutionServiceTest {
    @Autowired
    private SolutionService solutionService;

    @Autowired
    private SolutionEntityJpaRepository solutionEntityJpaRepository;

    private Long savedId;

    @BeforeEach
    @Transactional
    void setUp() {
        SolutionEntity entity = SolutionEntity.builder()
                .amount(1000L)
                .companySeq(1L)
                .duration(30L)
                .solutionName("test")
                .cloud(CLOUD.SaaS)
                .field(FIELD.ERP)
                .status(STATUS.WAITING_OCCUPY)
                .description("desc")
                .detailImage("image")
                .build();

        // 저장 후 반환값이 null인지 체크
        SolutionEntity saved = solutionEntityJpaRepository.save(entity);
        if (saved == null) throw new IllegalStateException("Save 실패: 엔티티가 null로 반환됨");

        this.savedId = saved.getSolutionSeq();
    }

    @Test
    @DisplayName("동시에 점유 시도해도 한 명만 성공해야 함")
    void 동시에_점유_테스트() throws InterruptedException {
        // given
        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger();

        // when
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    solutionService.findLock(savedId);
                    System.out.println(Thread.currentThread().getName() + " → 점유 성공");
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " → 점유 실패: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // then
        // 한 명만 성공해야 함
        Assertions.assertThat(successCount.get()).isEqualTo(1);

        // 상태가 OCCUPIED로 바뀌었는지 검증
        SolutionEntity result = solutionEntityJpaRepository.findById(savedId)
                .orElseThrow(() -> new IllegalStateException("엔티티 조회 실패"));
        Assertions.assertThat(result.getStatus()).isEqualTo(STATUS.OCCUPIED);
    }
}

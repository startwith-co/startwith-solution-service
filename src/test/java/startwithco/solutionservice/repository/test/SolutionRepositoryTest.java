package startwithco.solutionservice.repository.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import startwithco.solutionservice.domain.CLOUD;
import startwithco.solutionservice.domain.FIELD;
import startwithco.solutionservice.domain.STATUS;
import startwithco.solutionservice.domain.SolutionEntity;
import startwithco.solutionservice.repository.SolutionEntityJpaRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles("test")
public class SolutionRepositoryTest {
    @Autowired
    private SolutionEntityJpaRepository solutionEntityJpaRepository;

    @Test
    @DisplayName("솔루션 등록")
    public void 솔루션_등록() {
        // given
        final SolutionEntity solution = SolutionEntity.builder()
                .companySeq(1L)
                .field(FIELD.CRM)
                .solutionName("AI 솔루션")
                .detailImage("http://image.url/detail.png")
                .description("AI 기술을 활용한 추천 시스템")
                .amount(100000L)
                .cloud(CLOUD.SaaS)
                .duration(30L)
                .status(STATUS.OCCUPIED)
                .build();

        // when
        SolutionEntity result = solutionEntityJpaRepository.save(solution);

        // then
        assertThat(result.getSolutionSeq()).isNotNull();
        assertThat(result.getCompanySeq()).isEqualTo(1L);
        assertThat(result.getField()).isEqualTo(FIELD.CRM);
        assertThat(result.getSolutionName()).isEqualTo("AI 솔루션");
        assertThat(result.getDetailImage()).isEqualTo("http://image.url/detail.png");
        assertThat(result.getDescription()).isEqualTo("AI 기술을 활용한 추천 시스템");
        assertThat(result.getAmount()).isEqualTo(100000L);
        assertThat(result.getCloud()).isEqualTo(CLOUD.SaaS);
        assertThat(result.getDuration()).isEqualTo(30L);
        assertThat(result.getStatus()).isEqualTo(STATUS.OCCUPIED);
    }
}

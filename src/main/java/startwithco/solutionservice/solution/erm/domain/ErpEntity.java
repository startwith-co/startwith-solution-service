package startwithco.solutionservice.solution.erm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.solutionservice.solution.SolutionEntity;

@Entity
@Table(name = "ERP_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@SuperBuilder
public class ErpEntity extends SolutionEntity {
    /*
     * [솔루션 기본 정보 입력]
     * 1. ERP 기술 특화 선택
     */
    @Column(name = "specialist", nullable = false)
    private String specialist;

    /*
     * [솔루션 상세 정보]
     * 1. 대표 이미지
     * 2. 솔루션 상세 설명 PDF
     * 3. 업무 효율 상승률
     * 4. 업무 처리 속도 상승률
     * 5. 업무 자동화 전환율
     * 6. 업무 시간 단축
     * 7. 비용 절감
     */
    @Column(name = "representative_image", nullable = false)
    private String representativeImage;

    @Column(name = "solution_detail_image", nullable = false)
    private String solutionDetailImage;

    @Column(name = "task_efficiency", nullable = false)
    private Double taskEfficiency;

    @Column(name = "task_speed_increase", nullable = false)
    private Double taskSpeedIncrease;

    @Column(name = "automation_ratio", nullable = false)
    private Double automationRatio;

    @Column(name = "time_reduction", nullable = false)
    private Double timeReduction;

    @Column(name = "cost_saving", nullable = false)
    private Long costSaving;
}
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
@Table(name = "ERM_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@SuperBuilder
public class ErmEntity extends SolutionEntity {
    /*
     * [솔루션 기본 정보 입력]
     * 1. ERP 기술 특화 선택
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "specialist", nullable = false)
    private SPECIALIST specialist;

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

    public enum SPECIALIST {
        NONE,                    // 없음
        FINANCE,                 // 재무/회계 특화
        PURCHASING,              // 구매/발주 특화
        INVENTORY_LOGISTICS,     // 재고/물류 특화
        SALES,                   // 영업/판매 특화
        HR_PAYROLL,              // 인사/급여 특화
        CRM_INTEGRATION,         // CRM 연동 특화
        REPORT_ANALYTICS         // 분석 리포트 특화
    }
}
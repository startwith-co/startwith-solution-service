package startwithco.solutionservice.solution.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.solutionservice.base.BaseTimeEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SOLUTION_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@SuperBuilder
public class SolutionEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_seq")
    private Long solutionSeq;

    @Column(name = "vendor_seq", nullable = false)
    private Long vendorSeq;

    /*
     * [솔루션 기본 정보 입력]
     * 1. 솔루션명
     * 2. 솔루션 카테고리
     * 3. 도입 가능 산업군
     * 4. 도입 추천 기업 규모
     * 5. 솔루션 구축 형태
     */
    @Column(name = "solution_name", nullable = false)
    private String solutionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CATEGORY category;

    public enum CATEGORY {
        BI,
        BPM,
        CMS,
        CRM,
        DMS,
        EAM,
        ECM,
        ERP,
        HR,
        HRM,
        KM,
        SCM,
        SI,
        SECURITY
    }

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "recommended_company_size", nullable = false)
    private String recommendedCompanySize;

    @Column(name = "solution_implementation_type", nullable = false)
    private String solutionImplementationType;

    /*
     * [판매 정보 입력]
     * 1. 판매가
     * 2. 판매 형태
     * 3. 솔루션 가격
     * 4. 개발 기간
     * */
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "sell_type", nullable = false)
    private SELL_TYPE sellType;

    @Column(name = "duration", nullable = false)
    private Long duration;

    public enum SELL_TYPE {
        SINGLE,
        SUBSCRIBE
    }
}

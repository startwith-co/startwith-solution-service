package startwithco.solutionservice.solution;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.solutionservice.solution.util.INDUSTRY;
import startwithco.solutionservice.solution.util.RECOMMENDED_COMPANY_SIZE;
import startwithco.solutionservice.solution.util.SERVICE_TYPE;
import startwithco.solutionservice.solution.util.TAG;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SOLUTION_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@SuperBuilder
public class SolutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_seq")
    private Long solutionSeq;

    @Column(name = "vendor_seq", nullable = false)
    private Long vendorSeq;

    /*
     * [솔루션 기본 정보 입력]
     * 1. 솔루션명
     * 2. 도입 산업군
     * 3. 도입 추천 기업 규모
     * 4. 적용 가능 태그
     * 5. 서비스 형태
     */
    @Column(name = "solution_name", nullable = false)
    private String solutionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "industry", nullable = false)
    private INDUSTRY industry;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommended_company_size", nullable = false)
    private RECOMMENDED_COMPANY_SIZE recommendedCompanySize;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private TAG tag;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private SERVICE_TYPE serviceType;

    /*
     * [판매 정보 입력]
     * 1. 판매가
     * 2. 할인율
     * 3. 서비스 가격
     * 4. 개발 기간
     */
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "service_amount", nullable = false)
    private Long serviceAmount;

    @Column(name = "duration", nullable = false)
    private Long duration;
}

package startwithco.solutionservice.solution.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SOLUTION_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
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
     * 2. 서비스 카테고리
     * 3. ERP 기술 특화 선택
     * 4. 도입 산업군
     * 5. 도입 추천 기업 규모
     * 6. 적용 가능 태그
     * 7. 서비스 형태
     * */
    @Column(name = "solution_name", nullable = false)
    private String solutionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CATEGORY category;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialist", nullable = false)
    private SPECIALIST specialist;

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
     * */
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "service_amount", nullable = false)
    private Long serviceAmount;

    @Column(name = "duration", nullable = false)
    private Long duration;

    /*
     * [솔루션 상세 정보]
     * 1. 대표 이미지
     * 2. 솔루션 상세 설명 PDF
     * 3. 업무 효율 상승률
     * 4. 업무 처리 속도 상승률
     * 5. 업무 자동화 전환율
     * 6. 업무 시간 단축
     * 7. 비용 절감
     * */
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

    public enum CATEGORY {
        ERP,
        CRM,
        HRM,
        SCM,
        BI,
        FINANCE,
        BPM,
        CMS,
        EAM,
        KM,
        DMS,
        ECM,
        SI
    }

    public enum SPECIALIST {
        NONE,                    // 없음
        FINANCE,                 // 재무/회계 특화
        PURCHASING,             // 구매/발주 특화
        INVENTORY_LOGISTICS,    // 재고/물류 특화
        SALES,                  // 영업/판매 특화
        HR_PAYROLL,             // 인사/급여 특화
        CRM_INTEGRATION,        // CRM 연동 특화
        REPORT_ANALYTICS        // 분석 리포트 특화
    }

    public enum INDUSTRY {
        MANUFACTURING,         // 제조
        LOGISTICS,             // 물류/유통
        IT_TELECOMMUNICATION,  // 정보통신/IT
        CONSTRUCTION,          // 건설
        FINANCE,               // 금융
        REAL_ESTATE,           // 부동산/임대
        ACCOMMODATION,         // 숙박
        FOOD_SERVICE,          // 요식/외식
        ARTS,                  // 예술
        SPORTS,                // 스포츠
        ENVIRONMENT_ENERGY,    // 환경/에너지
        EDUCATION,             // 교육
        PUBLIC,                // 공공
        AGRICULTURE_FORESTRY_FISHERY // 농업/임업/어업
    }

    public enum RECOMMENDED_COMPANY_SIZE {
        SMALL_BUSINESS,     // 중소상공인
        STARTUP,            // 소기업
        MEDIUM_BUSINESS,    // 중기업
        UPPER_MEDIUM,       // 중견기업
        ENTERPRISE          // 대기업
    }

    public enum TAG {
        CUSTOMIZATION_AVAILABLE, // 고객 맞춤형(커스터마이징 가능)
        AI_APPLIED,              // AI 기술 적용
        NONE                     // 없음
    }

    public enum SERVICE_TYPE {
        SAAS,    // SaaS
        ON_PREM  // 구축 (또는 BUILD)
    }
}

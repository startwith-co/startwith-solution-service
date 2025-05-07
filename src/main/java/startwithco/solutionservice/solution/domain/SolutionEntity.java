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

    @Column(name = "solution_name", nullable = false)
    private String solutionName;

    @Column(name = "detail_image", nullable = false)
    private String detailImage;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "cloud", nullable = false)
    private CLOUD cloud;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @Column(name = "sell_count", nullable = false, columnDefinition = "bigint default 0")
    private Long sellCount;

    public enum CLOUD {
        PaaS,
        IaaS,
        SaaS,
        CaaS
    }

    public void upSellCount() {
        this.sellCount = this.sellCount + 1;
    }

    public void downSellCount() {
        if (this.sellCount > 0) {
            this.sellCount = this.sellCount - 1;
        }
    }
}

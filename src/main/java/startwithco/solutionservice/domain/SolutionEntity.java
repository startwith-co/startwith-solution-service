package startwithco.solutionservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.solutionservice.domain.type.CLOUD;
import startwithco.solutionservice.domain.type.FIELD;
import startwithco.solutionservice.domain.type.STATUS;

@Entity
@Table(name = "SOLUTION")
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

    @Column(name = "company_seq", nullable = false)
    private Long companySeq;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FIELD field;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private STATUS status;

    public void occupy() {
        if (this.status != STATUS.WAITING_OCCUPY) {
            throw new IllegalStateException("이미 점유된 솔루션입니다.");
        }

        this.status = STATUS.OCCUPIED;
    }
}

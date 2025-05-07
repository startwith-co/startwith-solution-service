package startwithco.solutionservice.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.solutionservice.solution.domain.SolutionEntity;

@Entity
@Table(name = "SOLUTION_REVIEW_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
public class SolutionReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_review_seq")
    private Long solutionReviewSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solution_seq")
    private SolutionEntity solutionEntity;

    @Column(name = "consumer_seq", nullable = false)
    private Long consumerSeq;

    @Column(name = "star", nullable = false)
    private Double star;
}

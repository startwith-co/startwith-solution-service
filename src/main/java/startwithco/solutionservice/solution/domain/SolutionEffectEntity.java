package startwithco.solutionservice.solution.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.solutionservice.base.BaseTimeEntity;

@Entity
@Table(name = "SOLUTION_EFFECT_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@SuperBuilder
public class SolutionEffectEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_effect_seq")
    private Long solutionEffectSeq;

    @Column(name = "effect_name", nullable = false)
    private String effectName;

    @Column(name = "percent", nullable = false)
    private Long percent;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false)
    private DIRECTION direction;

    public enum DIRECTION {
        INCREASE,
        DECREASE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solution_seq")
    private SolutionEntity solutionEntity;
}

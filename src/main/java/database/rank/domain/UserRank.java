package database.rank.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
public class UserRank implements Serializable {
    @Id
    @Column(name = "user_rank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RankName name;

    @NotNull
    @PositiveOrZero
    private Long minPoints;

    @NotNull
    @PositiveOrZero
    private Long maxPoints;

    @NotNull
    @PositiveOrZero
    private Double discount;


}

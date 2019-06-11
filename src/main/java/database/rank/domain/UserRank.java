package database.rank.domain;

import javax.persistence.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "rank")
@Data
@NoArgsConstructor
public class UserRank {
    @Id
    @Column(name = "rank_id")
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

    private String description;


}

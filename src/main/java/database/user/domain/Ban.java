package database.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Embeddable
//@Table(name = "ban")
public class Ban {

    private Long adminId;

    @NotNull
    @NotBlank
    private Boolean banned;

    @ElementCollection
    private List<?> banHistory;


}

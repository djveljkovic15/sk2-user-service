package database.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Embeddable
public class Ban {

//    private Long adminId;

//    @NotNull
//    @NotBlank
//    private Boolean banned;
//
//    @ElementCollection
//    private List<?> banHistory;

    @NotNull
    private String bannedBy;

    @DateTimeFormat //"2012-04-23T18:25:43.511Z"
    @NotNull
    private Date date;

    public Date setDate(){
        return new Date();
    }




}

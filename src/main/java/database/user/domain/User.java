package database.user.domain;

import database.rank.domain.UserRank;
import database.role.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @Column(name = "surname")
//    private String surname;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    private boolean isPasswordValid(String plainText){
        return plainText!= null &&
        plainText.length()>= 3 &&
        plainText.length()<= 12;
    }
    public void setPassword(String password){
        if (!isPasswordValid(password))
            throw new IllegalArgumentException("Password is invalid");
        this.password=password;
    }

    @NotNull
    private boolean banned;

    @Embedded
    @ElementCollection
    private List<Ban> banHistory;



    @Email // validacija za email
    @NotBlank
    @NotNull
    @Column(name = "email")
    private String email;

    public void setEmail(String email){
        if(EmailValidator.getInstance().isValid(email))
            this.email=email;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_role")
    private Role userRole;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_rank")
    private UserRank userRank;


    @NotBlank
    @NotNull
    @Column(name = "numberOfReservation")
    private String numberOfReservations;


}

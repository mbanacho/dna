package pl.mbanacho.dna.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    private String login;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private LocalDateTime creationDate;

}

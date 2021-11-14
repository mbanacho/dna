package pl.mbanacho.dna.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Job extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobCategory category;

    @NotNull
    @Basic
    private LocalDate startDate;

    @Basic
    private LocalDate endDate;

    private String country;

    @NotNull
    @ManyToOne
    private User employer;
}

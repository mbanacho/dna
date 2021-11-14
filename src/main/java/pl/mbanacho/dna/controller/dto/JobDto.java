package pl.mbanacho.dna.controller.dto;

import pl.mbanacho.dna.repository.JobCategory;

import java.time.LocalDate;

public record JobDto(JobCategory category, LocalDate startDate, LocalDate endDate, String country,
                     String employerName) {
}

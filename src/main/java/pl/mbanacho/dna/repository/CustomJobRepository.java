package pl.mbanacho.dna.repository;

import java.util.List;

public interface CustomJobRepository {
    List<Job> getCurrentValidJobsByEmployerNameAndCountry(String employerName, JobCategory country);
}

package pl.mbanacho.dna.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mbanacho.dna.controller.dto.JobDto;
import pl.mbanacho.dna.repository.Job;
import pl.mbanacho.dna.repository.JobCategory;
import pl.mbanacho.dna.repository.JobRepository;
import pl.mbanacho.dna.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;

    public List<JobDto> getCurrentValidJobs(String name, JobCategory category) {
        List<Job> jobs = jobRepository.getCurrentValidJobsByEmployerNameAndCountry(name, category);
        return jobMapper.mapJob(jobs);
    }

    public JobDto saveNewJob(JobDto jobDto) {
        return userRepository.findByName(jobDto.employerName())
                .map(user -> jobRepository.save(jobMapper.mapJob(jobDto, user)))
                .map(jobMapper::mapJob)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("User with name %s does not exists", jobDto.employerName())));
    }
}

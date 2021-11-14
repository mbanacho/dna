package pl.mbanacho.dna.service;

import org.springframework.stereotype.Service;
import pl.mbanacho.dna.controller.dto.JobDto;
import pl.mbanacho.dna.repository.Job;
import pl.mbanacho.dna.repository.User;

import java.util.List;

@Service
public class JobMapper {

    public List<JobDto> mapJob(List<Job> jobs) {
        return jobs.stream()
                .map(this::mapJob)
                .toList();
    }

    public JobDto mapJob(Job job) {
        return new JobDto(job.getCategory(), job.getStartDate(),
                job.getEndDate(), job.getCountry(), job.getEmployer().getName());

    }

    public Job mapJob(JobDto jobDto, User user) {
        Job job = new Job();
        job.setCategory(jobDto.category());
        job.setCountry(jobDto.country());
        job.setStartDate(jobDto.startDate());
        job.setEndDate(jobDto.endDate());
        job.setEmployer(user);
        return job;
    }
}

package pl.mbanacho.dna.service;

import org.junit.jupiter.api.Test;
import pl.mbanacho.dna.controller.dto.JobDto;
import pl.mbanacho.dna.repository.Job;
import pl.mbanacho.dna.repository.JobCategory;
import pl.mbanacho.dna.repository.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JobMapperTest {

    JobMapper testedClass = new JobMapper();

    @Test
    public void mapCorrectJobToJobDto_shouldHaveSameValue() {
        //GIVEN
        Job job = new Job();
        job.setEmployer(createUser());
        job.setCategory(JobCategory.IT);
        job.setStartDate(LocalDate.now());
        job.setEndDate(LocalDate.now());
        job.setCountry("SomeCountry");
        //WHEN
        JobDto jobDto = testedClass.mapJob(job);
        //THEN
        assertThat(jobDto.category()).isEqualTo(job.getCategory());
        assertThat(jobDto.country()).isEqualTo(job.getCountry());
        assertThat(jobDto.startDate()).isEqualTo(job.getStartDate());
        assertThat(jobDto.endDate()).isEqualTo(job.getEndDate());
        assertThat(jobDto.employerName()).isEqualTo(job.getEmployer().getName());
    }

    @Test
    public void emptyJobToJobDto_shouldHaveSameValue() {
        //GIVEN
        Job job = new Job();
        job.setEmployer(new User());

        //WHEN
        JobDto jobDto = testedClass.mapJob(job);
        //THEN
        assertThat(jobDto.category()).isEqualTo(job.getCategory());
        assertThat(jobDto.country()).isEqualTo(job.getCountry());
        assertThat(jobDto.startDate()).isEqualTo(job.getStartDate());
        assertThat(jobDto.endDate()).isEqualTo(job.getEndDate());
    }

    private User createUser() {
        User user = new User();
        user.setCreationDate(LocalDateTime.now());
        user.setPassword("password");
        user.setLogin("login");
        user.setName("name");
        return user;
    }
}
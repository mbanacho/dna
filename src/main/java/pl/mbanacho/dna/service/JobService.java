package pl.mbanacho.dna.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mbanacho.dna.controller.dto.JobDto;
import pl.mbanacho.dna.repository.Job;
import pl.mbanacho.dna.repository.JobRepository;
import pl.mbanacho.dna.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private final EntityManager entityManager;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;

    public List<JobDto> getCurrentValidJobs(Optional<String> name, Optional<String> category) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> query = criteriaBuilder.createQuery(Job.class);
        Root<Job> jobRoot = query.from(Job.class);
        Predicate[] predicates = getPredicates(name, category, criteriaBuilder, jobRoot);
        query.select(jobRoot).where(criteriaBuilder.and(predicates));
        List<Job> jobs = entityManager.createQuery(query).getResultList();
        return jobMapper.mapJob(jobs);
    }

    private Predicate[] getPredicates(Optional<String> name, Optional<String> category, CriteriaBuilder criteriaBuilder, Root<Job> jobRoot) {
        List<Predicate> predicates = new ArrayList<>();
        prepareCurrentValidJobPredicates(criteriaBuilder, jobRoot, predicates);
        name.ifPresent(s -> predicates.add(criteriaBuilder.equal(jobRoot.get("employer").get("name"), s)));
        category.ifPresent(s -> predicates.add(criteriaBuilder.equal(jobRoot.get("category"), s)));
        return predicates.toArray(new Predicate[0]);
    }

    private void prepareCurrentValidJobPredicates(CriteriaBuilder criteriaBuilder, Root<Job> job, List<Predicate> predicates) {
        Expression<LocalDate> actualDate = criteriaBuilder.literal(LocalDate.now());
        predicates.add(criteriaBuilder.lessThanOrEqualTo(job.get("startDate"), actualDate));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(job.get("endDate"), actualDate));
    }

    public JobDto saveNewJob(JobDto jobDto) {
        return userRepository.findByName(jobDto.employerName())
                .map(user -> jobRepository.save(jobMapper.mapJob(jobDto, user)))
                .map(jobMapper::mapJob)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("User with name %s does not exists", jobDto.employerName())));
    }
}

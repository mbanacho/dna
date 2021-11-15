package pl.mbanacho.dna.repository;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CustomJobRepositoryImpl implements CustomJobRepository{

    private final EntityManager entityManager;

    @Override
    public List<Job> getCurrentValidJobsByEmployerNameAndCountry(String employerName, JobCategory jobCategory) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> query = getJobCriteriaQuery(employerName, jobCategory, criteriaBuilder);
        return entityManager.createQuery(query).getResultList();
    }

    private CriteriaQuery<Job> getJobCriteriaQuery(String employerName, JobCategory jobCategory, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Job> query = criteriaBuilder.createQuery(Job.class);
        Root<Job> jobRoot = query.from(Job.class);
        List<Predicate> predicates = getPredicates(employerName, jobCategory, criteriaBuilder, jobRoot);
        query.select(jobRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        return query;
    }

    private List<Predicate> getPredicates(String employerName, JobCategory jobCategory, CriteriaBuilder criteriaBuilder, Root<Job> jobRoot) {
        List<Predicate> predicates = new ArrayList<>();
        prepareCurrentValidJobPredicates(criteriaBuilder, jobRoot, predicates);
        if(employerName != null) {
            predicates.add(criteriaBuilder.equal(jobRoot.get("employer").get("name"), employerName));
        }
        if(jobCategory != null) {
            predicates.add(criteriaBuilder.equal(jobRoot.get("category"), jobCategory));
        }
        return predicates;
    }

    private void prepareCurrentValidJobPredicates(CriteriaBuilder criteriaBuilder, Root<Job> job, List<Predicate> predicates) {
        Expression<LocalDate> actualDate = criteriaBuilder.literal(LocalDate.now());
        predicates.add(criteriaBuilder.lessThanOrEqualTo(job.get("startDate"), actualDate));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(job.get("endDate"), actualDate));
    }
}

package pl.mbanacho.dna.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mbanacho.dna.controller.dto.JobDto;
import pl.mbanacho.dna.repository.JobCategory;
import pl.mbanacho.dna.service.JobService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class JobController {

    private JobService jobService;

    @GetMapping("/job")
    public List<JobDto> getCurrentValidJobsByNameOrCategory(@RequestParam(value = "name", required = false) Optional<String> name,
                                                            @RequestParam(value = "category", required = false) Optional<JobCategory> category) {
        return jobService.getCurrentValidJobs(name.orElse(null), category.orElse(null));
    }

    @PostMapping("/job")
    public JobDto addNewJobOffer(@RequestBody JobDto job) {
        return jobService.saveNewJob(job);
    }
}

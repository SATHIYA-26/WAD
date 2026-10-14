package com.example.courseregistration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseRegistrationController {

    private final CourseRegistrationRepository repository;

    public CourseRegistrationController(CourseRegistrationRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseRegistration register(@RequestBody CourseRegistration registration) {
        registration.setId(null);
        return repository.save(registration);
    }

    @GetMapping
    public List<CourseRegistration> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CourseRegistration getById(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registration not found"));
    }

    @PutMapping("/{id}")
    public CourseRegistration update(@PathVariable String id,
                                     @RequestBody CourseRegistration updated) {
        CourseRegistration existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registration not found"));

        existing.setStudentName(updated.getStudentName());
        existing.setRegisterNumber(updated.getRegisterNumber());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        existing.setCourseName(updated.getCourseName());

        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Registration not found");
        }
        repository.deleteById(id);
    }
}
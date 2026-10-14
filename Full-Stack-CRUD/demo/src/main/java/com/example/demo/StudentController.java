
package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        s.setId(null);
        return repo.save(s);
    }

    // READ ALL
    @GetMapping
    public List<Student> getStudents() {
        return repo.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable String id) {

        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String id,
            @RequestBody Student s) {



        s.setId(id);
        return ResponseEntity.ok(repo.save(s));
    }

    @GetMapping("/search")
    public Student searchStudent(@RequestParam String name) {
        return repo.findByName(name);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable String id) {


        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

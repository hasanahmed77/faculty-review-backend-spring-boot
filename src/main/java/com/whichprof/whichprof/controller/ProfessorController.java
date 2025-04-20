package com.whichprof.whichprof.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.whichprof.whichprof.exceptions.InvalidProfessorID;
import com.whichprof.whichprof.model.Professor;
import com.whichprof.whichprof.model.Review;
import com.whichprof.whichprof.service.ProfessorService;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @ExceptionHandler(InvalidProfessorID.class)
    public ResponseEntity<String> handleInvalidProfessorID(InvalidProfessorID exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> bulkInsertProfessors(@RequestBody List<Professor> professors) {
        professorService.bulkInsertProfessors(professors);
        return ResponseEntity.ok("Bulk insert successful");
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor createdProfessor = professorService.saveProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessor);
    }

    @GetMapping
    public ResponseEntity<Page<Professor>> getProfessors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); // Create Pageable object
        Page<Professor> professors = professorService.getProfessors(pageable); // Call the service method

        return ResponseEntity.ok(professors); // Return the paginated result
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable String id) {
        Optional<Professor> professor = professorService.getProfessorById(id);
        return professor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable String id) {
        try {
            professorService.deleteProfessor(id);
            return ResponseEntity.noContent().build();
        } catch (InvalidProfessorID e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public Professor getProfessorByName(@PathVariable String name) {
        return professorService.getProfessorByName(name);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Professor> patchProfessor(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return professorService.patchProfessor(id, updates)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/reviews/{id}")
    public ResponseEntity<Professor> addReviews(@PathVariable String id, @RequestBody List<Review> newReviews) {
        Professor updatedProfessor = professorService.appendProfessorReviews(id, newReviews);
        return ResponseEntity.ok(updatedProfessor);
    }
}

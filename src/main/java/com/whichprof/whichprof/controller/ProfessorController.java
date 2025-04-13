package com.whichprof.whichprof.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whichprof.whichprof.model.Professor;
import com.whichprof.whichprof.service.ProfessorService;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.saveProfessor(professor);
    }

    @GetMapping
    public List<Professor> getProfessors() {
        return professorService.getAllProfessors();
    }

    @GetMapping("/{id}")
    public Optional<Professor> getProfessorById(@PathVariable String id) {
        return professorService.getProfessorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable String id) {
        professorService.deleteProfessor(id);
    }

    @GetMapping("/name/{name}")
    public Professor getProfessorByName(@PathVariable String name) {
        return professorService.getProfessorByName(name);
    }

    @PatchMapping("/{id}/reviews")
    public ResponseEntity<Professor> updateProfessorReviews(
            @PathVariable String id,
            @RequestBody List<String> newReviews) {

        Optional<Professor> optionalProfessor = professorService.getProfessorById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            List<String> existingReviews = professor.getReviews();
            existingReviews.addAll(newReviews); // Append reviews
            professor.setReviews(existingReviews);
            professorService.saveProfessor(professor);
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProfessor(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return professorService.patchProfessor(id, updates)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

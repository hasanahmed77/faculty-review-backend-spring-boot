package com.whichprof.whichprof.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
}

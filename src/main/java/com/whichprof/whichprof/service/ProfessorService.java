package com.whichprof.whichprof.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whichprof.whichprof.repository.ProfessorRepository;
import com.whichprof.whichprof.model.Professor;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Optional<Professor> getProfessorById(String id) {
        return professorRepository.findById(id);
    }

    public void deleteProfessor(String id) {
        professorRepository.deleteById(id);
    }

    public Professor getProfessorByName(String name) {
        return professorRepository.findByName(name);
    }
}

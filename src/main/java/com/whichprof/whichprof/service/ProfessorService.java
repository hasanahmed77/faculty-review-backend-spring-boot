package com.whichprof.whichprof.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whichprof.whichprof.repository.ProfessorRepository;
import com.whichprof.whichprof.exceptions.InvalidProfessorID;
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

    public Professor appendProfessorReviews(String id, List<String> newReviews) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            List<String> currentReviews = professor.getReviews();
            currentReviews.addAll(newReviews);
            professor.setReviews(currentReviews);
            return professorRepository.save(professor);
        } else {
            throw new InvalidProfessorID("Professor not found with id: " + id);
        }
    }

    public Optional<Professor> patchProfessor(String id, Map<String, Object> updates) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isEmpty()) {
            return Optional.empty(); // Professor not found
        }

        Professor professor = optionalProfessor.get();

        // Iterate over the updates and apply changes to the professor object
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    professor.setName((String) value);
                    break;
                case "dept":
                    professor.setDept((String) value);
                    break;
                case "initial":
                    professor.setInitial((String) value);
                    break;
                case "rating":
                    professor.setRating((Integer) value);
                    break;
                case "takeAgain":
                    professor.setTakeAgain((Boolean) value);
                    break;
                case "difficulty":
                    professor.setDifficulty((String) value);
                    break;
                case "courseName":
                    professor.setCourseName((String) value);
                    break;
                case "reviews":
                    if (value instanceof List<?>) {
                        @SuppressWarnings("unchecked")
                        List<String> reviews = (List<String>) value;
                        professor.setReviews(reviews);
                    }
                    break;
                case "university":
                    professor.setUniversity((String) value);
                    break;
                // Optionally: skip unknown fields or log errors
            }
        });

        // Save the updated professor object
        return Optional.of(professorRepository.save(professor));
    }

}

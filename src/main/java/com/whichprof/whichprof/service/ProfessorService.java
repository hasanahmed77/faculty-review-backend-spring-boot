package com.whichprof.whichprof.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.whichprof.whichprof.repository.ProfessorRepository;
import com.whichprof.whichprof.exceptions.InvalidProfessorID;
import com.whichprof.whichprof.model.Professor;
import com.whichprof.whichprof.model.Review; // Make sure to import the Review model

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public void bulkInsertProfessors(List<Professor> professors) {
        // Use saveAll method of MongoRepository for bulk insert
        professorRepository.saveAll(professors);
    }

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Page<Professor> getProfessors(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }

    // Updated to return professors sorted by createdAt (descending)
    public List<Professor> getAllProfessorsSortedByCreatedAt() {
        return professorRepository.findAll(Sort.by(Sort.Order.desc("createdAt")));
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

    // Update to use List<Review> instead of List<Map<String, Object>>
    public Professor appendProfessorReviews(String id, List<Review> newReviews) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.getReviews().addAll(0, newReviews);
            return professorRepository.save(professor);
        } else {
            throw new InvalidProfessorID("Professor not found with id: " + id);
        }
    }

    // Update to use List<Review> instead of List<Map<String, Object>>
    public Optional<Professor> patchProfessor(String id, Map<String, Object> updates) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isEmpty()) {
            return Optional.empty();
        }

        Professor professor = optionalProfessor.get();

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
                case "courseName":
                    professor.setCourseName((String) value);
                    break;
                case "reviews":
                    if (value instanceof List<?>) {
                        @SuppressWarnings("unchecked")
                        List<Review> reviews = (List<Review>) value; // Change to List<Review>
                        professor.setReviews(reviews);
                    }
                    break;
                case "university":
                    professor.setUniversity((String) value);
                    break;
            }
        });

        return Optional.of(professorRepository.save(professor));
    }
}

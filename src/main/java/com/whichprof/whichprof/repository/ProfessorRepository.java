package com.whichprof.whichprof.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.whichprof.whichprof.model.Professor;
import org.springframework.data.domain.Pageable;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
    Professor findByName(String name);

    Page<Professor> findAll(Pageable pageable);
}

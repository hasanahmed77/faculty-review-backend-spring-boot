package com.whichprof.whichprof.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.whichprof.whichprof.model.Professor;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
    Professor findByName(String name);
}

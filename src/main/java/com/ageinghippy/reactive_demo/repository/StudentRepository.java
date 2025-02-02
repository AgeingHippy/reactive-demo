package com.ageinghippy.reactive_demo.repository;

import com.ageinghippy.reactive_demo.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student,Integer> {
}

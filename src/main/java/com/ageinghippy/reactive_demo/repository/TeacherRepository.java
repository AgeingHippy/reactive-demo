package com.ageinghippy.reactive_demo.repository;

import com.ageinghippy.reactive_demo.model.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeacherRepository extends ReactiveCrudRepository<Teacher, Integer> {
}

package com.ageinghippy.reactive_demo.service;

import com.ageinghippy.reactive_demo.model.Student;
import com.ageinghippy.reactive_demo.model.Teacher;
import com.ageinghippy.reactive_demo.repository.TeacherRepository;
import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@AllArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TransactionalOperator transactionalOperator;

    public Mono<Teacher> getTeacherById(Integer id) {
        return transactionalOperator.transactional(teacherRepository.findById(id));
    }

    public Flux<Teacher> getAllTeachers() {
        return transactionalOperator.transactional(teacherRepository.findAll());
    }

    public Mono<Teacher> createNewTeacher(Teacher teacher) {
        return transactionalOperator.transactional(teacherRepository.save(teacher));
    }

    public Mono<Teacher> createNewTeacher(Mono<Teacher> teacherMono) {
        return transactionalOperator.transactional(
                teacherMono.flatMap(teacherRepository::save));
    }

    public Mono<Void> deleteTeacher(Integer id) {
        return transactionalOperator.transactional(
                teacherRepository.deleteById(id));
    }

    public Flux<Teacher> saveTeachersFromArray(String[][] teacherArray) {
        final Flux<Teacher> teacherFlux = Flux.fromArray(teacherArray)
                .map(arr -> new Teacher(null, arr[0], arr[1]))
                .flatMap(this.teacherRepository::save);

        return transactionalOperator.transactional(teacherFlux);
    }

}

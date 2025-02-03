package com.ageinghippy.reactive_demo.configuration;

import com.ageinghippy.reactive_demo.model.Student;
import com.ageinghippy.reactive_demo.model.Teacher;
import com.ageinghippy.reactive_demo.repository.StudentRepository;
import com.ageinghippy.reactive_demo.repository.TeacherRepository;
import com.ageinghippy.reactive_demo.service.StudentService;
import com.ageinghippy.reactive_demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Flux.fromArray;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        this.studentRepository
                .deleteAll()
                .thenMany(studentService.saveAll(
                        "Evan", "Ji", "Harry", "bob", "Ryan", "Rick", "Chuck"))
                .onErrorContinue((e,i) -> System.out.println(e.getMessage() + " " + i.toString()))
                .thenMany(studentService.getAllStudents())
                .subscribe(s -> System.out.println(s.getName()));

        this.teacherRepository
                .deleteAll()
                .thenMany( teacherService.saveTeachersFromArray(new String[][]{{"bob","maths"},{"bill","Science"},{"bean","stience"}}))
                .thenMany( teacherService.getAllTeachers())
                .subscribe(t -> System.out.println(t.toString()));

    }


}


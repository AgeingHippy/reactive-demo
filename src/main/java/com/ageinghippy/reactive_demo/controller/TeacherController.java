package com.ageinghippy.reactive_demo.controller;

import com.ageinghippy.reactive_demo.model.Teacher;
import com.ageinghippy.reactive_demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Component
public class TeacherController {

    private final TeacherService teacherService;

    @Bean
    public RouterFunction<ServerResponse> getTeacherById() {
        return route()
                .GET("/teachers/{id}",
                        req -> {
                            Integer id = Integer.parseInt(req.pathVariable("id"));
                            return ok().body(teacherService.getTeacherById(id), Teacher.class);
                        })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> getAllTeachers() {
        return route()
                .GET("/teachers", request -> {
                    return ok().body(teacherService.getAllTeachers(), Teacher.class);
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> createNewTeacher() {
        return route()
                .POST("/teachers", request -> {
                    Mono<Teacher> teacherMono = request.bodyToMono(Teacher.class);
                    return ok().body(teacherService.createNewTeacher(teacherMono), Teacher.class);
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deleteTeacher() {
        return route()
                .DELETE("/teachers/{id}",
                        request -> {
                            Integer id = Integer.parseInt(request.pathVariable("id"));
                            return teacherService.deleteTeacher(id)
                                    .then(ServerResponse.ok().build());
                        })
                .build();
    }

}

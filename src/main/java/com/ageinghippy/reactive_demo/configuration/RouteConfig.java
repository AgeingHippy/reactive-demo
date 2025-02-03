package com.ageinghippy.reactive_demo.configuration;

import com.ageinghippy.reactive_demo.controller.StudentController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {

    @Bean
    RouterFunction<ServerResponse> routes(
            StudentController studentController) {

        return route()
                .GET("/students",
                        accept(MediaType.APPLICATION_JSON),
                        studentController::getAllStudents)
                .GET("/students/{id}",
                        accept(MediaType.APPLICATION_JSON),
                        studentController::getStudent)
                .POST("/students",
                        contentType(MediaType.APPLICATION_JSON),
                        studentController::addNewStudent)
                .PUT("/students/{id}",
                        contentType(MediaType.APPLICATION_JSON),
                        studentController::updateStudent)
                .DELETE("/students/{id}",
                        studentController::deleteStudent)
                .build();
    }
}


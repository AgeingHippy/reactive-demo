package com.ageinghippy.reactive_demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    @Id
    private Integer id;
    private String name;
}


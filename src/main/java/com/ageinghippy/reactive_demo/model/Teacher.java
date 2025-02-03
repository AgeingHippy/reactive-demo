package com.ageinghippy.reactive_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Teacher {

    @Id
    private Integer id;
    private String Name;
    private String Subject;
}

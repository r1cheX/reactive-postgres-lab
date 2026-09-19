package com.learning.reactive_postgres_lab.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class StudentEntity {
    @Id
    private String id;
    private String name;
    private Integer age;
    private boolean active;
    private List<String> skills;

}

package com.learning.reactive_postgres_lab.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class StudentDto {
    @NotNull
    private String studentCode;

    private String email;

    private String firstName;

    private String lastName;

    @NotNull
    private boolean active;

    @NotEmpty
    private List<String> listCourses;
}

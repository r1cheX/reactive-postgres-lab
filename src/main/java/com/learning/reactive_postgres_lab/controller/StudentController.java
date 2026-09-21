package com.learning.reactive_postgres_lab.controller;

import com.learning.reactive_postgres_lab.dto.SearchFilterDto;
import com.learning.reactive_postgres_lab.dto.StudentDto;
import com.learning.reactive_postgres_lab.service.StudentService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/search")
    public Flux<StudentDto> search(@RequestBody SearchFilterDto searchFilterDto) {
        return studentService.searchStudents(searchFilterDto);
    }


}

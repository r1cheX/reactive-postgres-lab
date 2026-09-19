package com.learning.reactive_postgres_lab.service;

import com.learning.reactive_postgres_lab.dto.SearchFilterDto;
import com.learning.reactive_postgres_lab.dto.StudentDto;
import reactor.core.publisher.Flux;

public interface StudentService {
    Flux<StudentDto> searchStudents(SearchFilterDto searchFilterDto);

}

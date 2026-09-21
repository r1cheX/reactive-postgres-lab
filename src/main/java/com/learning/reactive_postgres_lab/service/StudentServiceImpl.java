package com.learning.reactive_postgres_lab.service;

import com.learning.reactive_postgres_lab.dto.SearchFilterDto;
import com.learning.reactive_postgres_lab.dto.StudentDto;
import com.learning.reactive_postgres_lab.model.StudentEntity;
import com.learning.reactive_postgres_lab.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Flux<StudentDto> searchStudents(SearchFilterDto searchFilterDto) {
        return studentRepository.findByFilter(searchFilterDto).map(this::mapTo);
    }

    private StudentDto mapTo(StudentEntity studentEntity) {
        return StudentDto.builder()
                .studentCode(studentEntity.getStudentCode())
                .email(studentEntity.getEmail())
                .active(studentEntity.getActive())
                .build();
    }



}

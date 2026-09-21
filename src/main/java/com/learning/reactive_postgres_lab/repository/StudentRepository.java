package com.learning.reactive_postgres_lab.repository;


import com.learning.reactive_postgres_lab.model.StudentEntity;
import com.learning.reactive_postgres_lab.repository.custom.StudentRepositoryCustom;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface StudentRepository extends R2dbcRepository<StudentEntity, Long>, StudentRepositoryCustom {
    Mono<StudentEntity> findByEmail(String email);
}

package com.learning.reactive_postgres_lab.repository.custom;


import com.learning.reactive_postgres_lab.dto.SearchFilterDto;
import com.learning.reactive_postgres_lab.model.StudentEntity;
import reactor.core.publisher.Flux;

public interface StudentRepositoryCustom  {
    Flux<StudentEntity> findByFilter(SearchFilterDto searchFilterDto);

}

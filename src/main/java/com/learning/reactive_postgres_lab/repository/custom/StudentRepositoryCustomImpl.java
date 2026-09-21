package com.learning.reactive_postgres_lab.repository.custom;

import com.learning.reactive_postgres_lab.dto.SearchFilterDto;
import com.learning.reactive_postgres_lab.model.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
@AllArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    // TODO: Bug no filtra exactamente esos campos
    @Override
    public Flux<StudentEntity> findByFilter(SearchFilterDto searchFilterDto) {
        Criteria criteria = Criteria.empty();

        if(hasData(searchFilterDto.studentCode())){
            criteria.and("student_code").is(searchFilterDto.studentCode());
        }

        if(hasData(searchFilterDto.email())){
            criteria.and("email").is(searchFilterDto.email());
        }

        if(hasData(searchFilterDto.firstName())){
            criteria.and("first_name").like("%" + searchFilterDto.firstName() + "%");
        }

        Query query = Query.query(criteria);
        return r2dbcEntityTemplate.select(query, StudentEntity.class);
    }

    private boolean hasData(String string) {
        return string != null && !string.isEmpty();
    }
}

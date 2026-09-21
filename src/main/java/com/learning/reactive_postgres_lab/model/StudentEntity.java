package com.learning.reactive_postgres_lab.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity {
    @Id
    private Long id;

    @Column("student_code")
    private String studentCode;

    private String email;

    private Boolean active;

    @Column("created_at")
    private LocalDateTime createdAt;
}

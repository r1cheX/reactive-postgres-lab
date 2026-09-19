---
name: reactive-sql-advanced
description: ReactiveCrudRepository vs R2dbcEntityTemplate vs DatabaseClient, safe dynamic SQL, row mapping, and PostgreSQL materialized views. Use for Levels 4-6 exercises in reactive-postgres-lab.
---

# Reactive SQL: repository vs template vs client

## When ReactiveCrudRepository is enough
- Basic CRUD and fixed-shape derived queries over a mapped relational table.
- Use `Mono` for one result and `Flux` for many results.

## When R2dbcEntityTemplate earns its keep
- Query shape depends on optional filters and the query can be expressed with Spring Data relational criteria.
- You need explicit table, column, update, or projection mapping without writing a full SQL string.

```java
// Build only the predicates represented by the request.
Query query = Query.query(Criteria.where("active").is(true));
r2dbcEntityTemplate.select(StudentEntity.class).matching(query).all();
```

## When DatabaseClient earns its keep
- The query needs joins, materialized views, PostgreSQL functions, or SQL-specific clauses.
- Bind every value with named parameters and map rows to a response DTO.

```java
databaseClient.sql("SELECT id, name, email FROM student_search_mv WHERE name ILIKE :name")
    .bind("name", namePrefix + "%")
    .map((row, metadata) -> new StudentResponse(row.get("id", Long.class), row.get("name", String.class)))
    .all();
```

## Materialized view and index basics
- Create and refresh `student_search_mv` in PostgreSQL/DataGrip.
- Map the view as a read-only projection or entity for query exercises.
- Use `EXPLAIN (ANALYZE, BUFFERS)` before and after simple and compound indexes.

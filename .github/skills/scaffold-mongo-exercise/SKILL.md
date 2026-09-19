---
name: scaffold-r2dbc-exercise
description: 'Scaffolds a lab exercise by generating HTTP, Controller, DTO, and Service method signatures, leaving only the core R2DBC / SQL / Reactor logic as a TODO for the user to implement.'
argument-hint: 'Exercise name, topic, or HTTP endpoint to scaffold'
user-invocable: true
---

# Scaffold PostgreSQL R2DBC Exercise

## Purpose
Accelerate learning in `reactive-postgres-lab` by handling repetitive boilerplate (Controller mappings, HTTP request/response handling, DTO creation, and Service interface signatures), leaving only the core **R2DBC**, **SQL**, or **Reactor** logic for the user to write.

## When to Use
Use this skill when:
- The user asks to start, prepare, or scaffold a new exercise (e.g., "scaffold GET /students/active", "prepare the exercise for dynamic criteria", "scaffold exercise Level 2").
- The user wants to focus strictly on PostgreSQL / R2DBC logic without spending time writing web boilerplate.
- The user uses keywords like "partial exercise", "scaffold exercise", or "focus on SQL".

## Procedure

1. **Identify Exercise & Scope**
   - Check `LAB_PROGRESS.md` or the user's prompt to determine the exercise requirements (HTTP method, URI, inputs, outputs).
   - Identify which layer contains the SQL / R2DBC / Reactor focus area (usually `StudentRepository`, `StudentServiceImpl`, or `StudentController`).

2. **Generate Non-Database Boilerplate**
   - **DTOs / Models**: Create or update DTO classes (`StudentDto`, request/response payloads) with appropriate Lombok annotations if needed.
   - **Controller Layer**: Implement the REST endpoint in `StudentController.java` with annotations (`@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestParam`), proper status codes, and HTTP responses wrapped in `Mono<ResponseEntity<T>>` or `Flux<T>`.
   - **Service Layer Signature**: Add the method signature to `StudentService.java` interface and stub out `StudentServiceImpl.java`.

3. **Insert Core R2DBC / SQL / Reactor TODOs & Method Stubs**
   - In the target location (repository method, template query, DatabaseClient query, or service implementation), place a clear `// TODO [USER FOCUS]:` comment describing the exact query or operator logic to implement.
   - For reactive returns, stub the method returning `Mono.error(new UnsupportedOperationException("Not implemented yet"))` or `Flux.error(new UnsupportedOperationException("Not implemented yet"))`.
    - Example `TODO` formats:
       - Repository: `// TODO [USER FOCUS]: Define the reactive relational query using a derived method or @Query`
       - R2dbcEntityTemplate: `// TODO [USER FOCUS]: Build the relational Query and Criteria objects`
       - DatabaseClient: `// TODO [USER FOCUS]: Write parameterized SQL and map each row to the response DTO`
     - Reactor Operators: `// TODO [USER FOCUS]: Implement transformation using map/flatMap/switchIfEmpty/onErrorResume`

4. **Update Lab Progress Tracking**
   - In `LAB_PROGRESS.md`, find the exercise being scaffolded and set its status to `pending` with today's date in the `Started` column.

5. **Present the Scaffolded Exercise to User**
   - List the files created or modified.
   - Highlight the exact file(s) and line number(s) where the `// TODO [USER FOCUS]:` is located.
   - Provide a sample Gradle / curl command or test setup for the user to verify their solution once they complete the `TODO`.

## Boilerplate vs Focus Rules

| Component | Handled by Skill (Boilerplate) | Left for User (Core Focus) |
|---|---|---|
| **Controller** | Mappings, HTTP Status, path/query param extraction | Mapping to Service calls (if exercise targets Controller) |
| **DTO / Model** | Fields, Getters/Setters/Lombok, conversion helpers | Custom projection interfaces or relational column mappings |
| Repository | Interface structure, standard CRUD signatures | Custom `@Query`, derived query names, or `ReactiveCrudRepository` methods |
| **Service Implementation** | Method signatures, dependency injection | R2dbcEntityTemplate / DatabaseClient queries or Reactor operator chains (`flatMap`, `switchIfEmpty`, etc.) |

## Example Output Structure

When scaffolding an exercise like `GET /students?active=true`:

- **Controller** (`StudentController.java`): Fully implemented endpoint calling `studentService.getStudentsOlderThan(age)`.
- **Service** (`StudentService.java` & `StudentServiceImpl.java`): Method defined returning `Flux<StudentDto>`.
- **Implementation Placeholder**:
  ```java
  @Override
  public Flux<StudentResponse> findStudents(Boolean active) {
     // TODO [USER FOCUS]: Build the parameterized reactive SQL query and map rows to StudentResponse
      return Flux.error(new UnsupportedOperationException("Not implemented yet"));
  }
  ```

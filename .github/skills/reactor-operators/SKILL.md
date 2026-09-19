---
name: reactor-operators
description: Reference for high-value Reactor operators with PostgreSQL R2DBC examples and a map-vs-flatMap decision checklist. Use for Level 3 exercises in reactive-postgres-lab.
---

# Reactor operators with PostgreSQL R2DBC

## map vs flatMap — decision checklist
1. Does the transform return a plain value (not a `Mono`/`Flux`)? → `map`
2. Does the transform call another reactive source (repository, service, another Mono)? → `flatMap`
3. Using `map` with a function that returns `Mono<X>` produces `Mono<Mono<X>>` — a common bug. If you see nested reactive types, you needed `flatMap`.

```java
// map: sync transform
    studentMono.map(StudentResponse::name);

// flatMap: chains another reactive call
studentRepository.findById(id)
    .flatMap(student -> addressRepository.findByStudentId(student.getId()));
```

## filter
```java
studentFlux.filter(StudentResponse::active);
```

## switchIfEmpty
```java
studentRepository.findById(id)
    .switchIfEmpty(Mono.error(new NotFoundException(id)));
// or provide a fallback value instead of erroring:
studentRepository.findById(id)
    .switchIfEmpty(Mono.just(Student.guest()));
```

## onErrorResume
```java
studentRepository.findById(id)
    .onErrorResume(DataAccessException.class, e -> Mono.empty());
```

## zip
```java
Mono.zip(studentRepository.findById(id1), studentRepository.findById(id2))
    .map(tuple -> combine(tuple.getT1(), tuple.getT2()));
```

## Common lab exercise shapes
- Transform a `StudentEntity` into a response DTO -> `map`
- Look up a related SQL row by id found on the first result -> `flatMap`
- Return 404 when a `findById` is empty → `switchIfEmpty`
- Convert a transient R2DBC error into a fallback -> `onErrorResume`
- Combine two independent PostgreSQL calls into one response -> `zip`

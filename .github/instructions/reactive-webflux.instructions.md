---
applyTo: "src/main/java/**/*.java"
---

# Reactive WebFlux and R2DBC coding conventions

- No blocking calls (`Thread.sleep`, `.block()`, `.toIterable()`) — except the deliberate Level 5 blocking-demo endpoint, which must be clearly named/commented as intentional.
- Prefer Mono/Flux composition over imperative style; avoid subscribing manually outside tests.
- `map` = synchronous 1:1 transform. `flatMap` = transform that returns a `Mono`/`Flux` (async/another reactive call). When both could apply, call out the choice in a one-line comment.
- Prefer `ReactiveCrudRepository`, `R2dbcEntityTemplate`, and `DatabaseClient` according to the current exercise.
- Bind every user-supplied SQL value; never concatenate request parameters into SQL.
- Use Spring Data relational annotations and explicit table/column mappings when the PostgreSQL schema requires them.
- Reuse existing skeleton classes instead of adding new packages/classes unless the exercise asks for it.

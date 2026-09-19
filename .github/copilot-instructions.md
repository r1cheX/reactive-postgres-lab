# reactive-postgres-lab - Copilot instructions

## Stack (facts, don't re-derive)
- Java 25 toolchain, Spring Boot 4.1.1, **Gradle** (`build.gradle`, not Maven)
- Spring WebFlux + Reactor, Spring Data R2DBC, PostgreSQL R2DBC driver, Lombok
- PostgreSQL via the configured local environment, inspected with DataGrip
- Package layout: `com.learning.reactive_postgres_lab.{controller,dto,model,service,repository}`
- Existing skeleton classes (`StudentEntity`, `StudentController`, `StudentService`, `StudentRepository`) are the implementation surface - build into them, don't create parallel structures

## Response rules
- Answer in English.
- Be concise by default: code + at most a 1-line rationale. No theory dumps unless explicitly asked.
- Never scaffold the whole project at once. This is a learning lab — one exercise at a time.
- Do not use document-database APIs, document templates, or document-database terminology in new work.
- Do not execute commands or run tests unless the user explicitly requests validation.

## Exercise workflow
- This repo is worked through progressively via `LAB_PROGRESS.md` (Levels 3-6 cover the current PostgreSQL curriculum).
- Before answering exercise-related questions, check `LAB_PROGRESS.md` for current level/status.
- Use `/next-exercise` to get the next exercise, `/review-exercise` to review current work against the pending one.
- Don't jump ahead levels or give the full solution to a pending exercise unless explicitly asked.

---
name: scaffold-exercises
description: Scaffold the next or specified exercises in reactive-postgres-lab, leaving only R2DBC/SQL/Reactor logic as a TODO
---

Find the target exercise in `LAB_PROGRESS.md` (either the next unstarted exercise or the one specified by the user).

Follow the R2DBC scaffolding skill instructions:
1. Generate all non-database boilerplate (Controller mappings, HTTP request/response handling, DTOs, Service method signatures).
2. Insert `// TODO [USER FOCUS]:` in the target implementation with `Mono.error(new UnsupportedOperationException("Not implemented yet"))` or `Flux.error(...)` stubs.
3. Update `LAB_PROGRESS.md` to set the exercise status to `pending` with today's date in the "Started" column.
4. Highlight the exact file and lines where the `// TODO [USER FOCUS]:` comment lives, and provide a curl or Gradle command to verify once implemented.

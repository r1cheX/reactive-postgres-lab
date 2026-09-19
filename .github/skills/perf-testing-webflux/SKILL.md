---
name: perf-testing-webflux
description: PostgreSQL EXPLAIN ANALYZE workflow, k6 concurrency testing snippets, and JFR/jcmd/VisualVM thread-inspection steps for reactive WebFlux endpoints. Use for Level 6 exercises in reactive-postgres-lab.
---

# Performance + threads workflow

## PostgreSQL EXPLAIN loop
1. Run the target query without an index: `EXPLAIN (ANALYZE, BUFFERS) SELECT ...`
2. Note `Seq Scan`, execution time, rows removed by filter, and rows returned.
3. Create the exercise index with `CREATE INDEX ...`.
4. Re-run the same statement and compare `Index Scan` or `Bitmap Index Scan`, execution time, and rows scanned.

## k6 load test skeleton
```javascript
import http from 'k6/http';
export const options = { vus: 100, duration: '30s' };
export default function () {
  http.get('http://localhost:8080/students');
}
```
Run: `k6 run --vus 10 script.js`, then repeat with `--vus 100`, `--vus 500`.

## Non-blocking vs blocking comparison endpoints
```java
@GetMapping("/non-blocking")
public Flux<Student> nonBlocking() { return studentRepository.findAll(); }

@GetMapping("/blocking-demo")
public Flux<Student> blockingDemo() {
    return studentRepository.findAll()
        .doOnNext(s -> { try { Thread.sleep(200); } catch (InterruptedException ignored) {} }); // intentional: simulates blocking work on event loop
}
```
Stress both with k6 and compare latency/throughput.

## Observing threads/JVM
- `jcmd <pid> Thread.print` — see if Netty event-loop threads are blocked (blocking endpoint should show them stuck in `Thread.sleep`).
- JFR: `jcmd <pid> JFR.start duration=60s filename=recording.jfr`, open in JDK Mission Control.
- VisualVM: attach to the running process, watch Threads tab under load.

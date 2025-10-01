# Java Coding Standards (Project-Wide)
**Naming**
- Packages: none (default package) to keep `javac Main.java` flows simple.
- Classes: `PascalCase` (e.g., `ChatRoom`, `PaymentAdapter`).
- Methods/variables: `camelCase` (e.g., `sendMessage`, `userName`).
- Constants: `UPPER_SNAKE_CASE`.
- Suffix conventions: Demo classes end with `Demo` (e.g., `ObserverChatDemo`).

**Structure per use case**
- `Main.java` at use-case root. `src/` contains all other classes.
- `Main.java` invokes JavaCompiler to compile `src/*.java` into `bin/` on first run.
- Demos expose either `public static void runDemo(java.util.Scanner in)` or a `public static void main(String[] args)`.

**Style**
- One public class per file; keep fields `private` and use getters where needed.
- Validate user input; show friendly messages.
- Prefer interfaces for roles; favor composition.
- Keep methods short and single-purpose.
- Add comments for non-obvious logic.
- Avoid `package` declarations to keep compilation simple for this exercise.
- No external libraries.

**How to run (any use case)**
```bash
javac Main.java
java Main
```
`Main` will compile the classes in `src/` automatically (using the system JDK compiler) and then present a menu.

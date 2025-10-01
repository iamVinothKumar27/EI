# Exercise 2 — Real-time Chat (Console, Java)

Console-based chat demonstrating **Observer**, **Singleton**, and **Adapter** patterns, plus a simple **Command** layer.
Structure mirrors your requested format: `Main.java` at root, all other classes inside `src/` with **no packages**.

## Run (macOS/Linux/Windows PowerShell)
```bash
cd exercise-2-realtime-chat-java
# Compile
javac Main.java src/*.java
# Run
java Main
```

> If your shell doesn't expand `src/*.java`, use:
> `javac Main.java src/Chat*.java src/*Command.java src/*Transport.java src/*Store.java src/*Session.java src/*Observer.java src/*Adapter.java src/*Factory.java 2>/dev/null || true`
> or simply `javac Main.java src/*.java` in bash/zsh/cmd.

## Features
- Create/join rooms, send public & private messages
- Observer pattern: `UserSession` receives updates in real-time
- Singleton: `ChatServer` holds rooms/state
- Adapter: `Transport` (`ConsoleTransport`, `WebSocketAdapter` stub)
- Command objects for core operations
- Message history per room
- Defensive validation & basic logging

## Sample Flow
```
1) Login as user -> "Alice"
2) Create room -> "Room123"
3) Join room -> "Room123"
2) Send message -> "Hello!"
5) Show message history
7) Switch user -> "Bob"
3) Join room -> "Room123"
2) Send message -> "Hi Alice"
4) Private message -> to "Alice" -> "secret"
```

## Notes
- This is a **single-process console** demo. Real networking (sockets/WebSockets) not included by design.
- All classes are in **default package** for simple `javac Main.java src/*.java` compilation without extra flags.

# Behavioral • Observer — Chat Room

**Pattern goal:** Notify all observers (users) whenever a message is posted in the room.

**Run**
```bash
javac Main.java
java Main
# choose: ObserverChatDemo
```

**User inputs**
- Room name
- Users to register (comma separated)
- Messages until you type `/exit`

**Classes**
- `ChatRoom` (Subject)
- `ChatObserver` (Observer interface)
- `ChatUser` (Concrete Observer)
- `ObserverChatDemo` (Demo harness)

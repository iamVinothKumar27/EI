# Behavioral • Command — Text Editor (Undo/Redo)

**Pattern goal:** Encapsulate operations as commands with undo/redo.

**Run**
```bash
javac Main.java
java Main
# choose: CommandTextEditorDemo
```

**User inputs**
- Text to insert / delete
- Use commands: `insert <text>`, `delete <n>`, `undo`, `redo`, `/exit`

**Classes**
- `Editor` (Receiver)
- `Command` (Command interface)
- `InsertCommand`, `DeleteCommand`
- `CommandTextEditorDemo` (Invoker & UI)

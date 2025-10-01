public class InsertCommand implements Command {
    private final Editor editor;
    private final String payload;

    public InsertCommand(Editor editor, String payload) {
        this.editor = editor;
        this.payload = payload;
    }

    @Override
    public void execute() {
        editor.insert(payload);
    }

    @Override
    public void undo() {
        editor.delete(payload.length());
    }
}

public class DeleteCommand implements Command {
    private final Editor editor;
    private final int n;
    private String removed = "";

    public DeleteCommand(Editor editor, int n) {
        this.editor = editor;
        this.n = n;
    }

    @Override
    public void execute() {
        removed = editor.delete(n);
    }

    @Override
    public void undo() {
        editor.insert(removed);
    }
}

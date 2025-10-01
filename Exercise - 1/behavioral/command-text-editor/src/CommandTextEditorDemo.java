import java.util.*;

public class CommandTextEditorDemo {
    public static void runDemo(Scanner in) {
        Editor editor = new Editor();
        Deque<Command> undo = new ArrayDeque<>();
        Deque<Command> redo = new ArrayDeque<>();

        System.out.println("Commands: insert <text> | delete <n> | undo | redo | /exit");
        while (true) {
            System.out.println("Current: \"" + editor.getText() + "\"");
            System.out.print("> ");
            String line = in.nextLine();
            if (line.equalsIgnoreCase("/exit")) break;
            if (line.startsWith("insert ")) {
                String text = line.substring(7);
                Command c = new InsertCommand(editor, text);
                c.execute();
                undo.push(c);
                redo.clear();
            } else if (line.startsWith("delete ")) {
                try {
                    int n = Integer.parseInt(line.substring(7).trim());
                    Command c = new DeleteCommand(editor, n);
                    c.execute();
                    undo.push(c);
                    redo.clear();
                } catch (NumberFormatException e) {
                    System.out.println("Provide a number for delete.");
                }
            } else if (line.equals("undo")) {
                if (undo.isEmpty()) { System.out.println("Nothing to undo."); continue; }
                Command c = undo.pop();
                c.undo();
                redo.push(c);
            } else if (line.equals("redo")) {
                if (redo.isEmpty()) { System.out.println("Nothing to redo."); continue; }
                Command c = redo.pop();
                c.execute();
                undo.push(c);
            } else {
                System.out.println("Unknown command.");
            }
        }
        System.out.println("Final: \"" + editor.getText() + "\"");
    }
}

package chatapp;

import java.util.*;
public class HistoryCommand implements Command {
    private final ChatServer server;
    private final String roomId;

    public HistoryCommand(ChatServer server, String roomId) {
        this.server = server; this.roomId = roomId;
    }

    @Override public void execute() {
        List<Message> msgs = server.getRoom(roomId).history();
        if (msgs.isEmpty()) {
            System.out.println("No history for room " + roomId);
            return;
        }
        System.out.println("=== History for room " + roomId + " ===");
        for (Message m : msgs) System.out.println(m.render());
    }
}

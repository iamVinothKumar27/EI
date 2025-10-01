package chatapp;
public class SendMessageCommand implements Command {
    private final ChatServer server;
    private final String roomId;
    private final UserSession from;
    private final String text;

    public SendMessageCommand(ChatServer server, String roomId, UserSession from, String text) {
        this.server = server; this.roomId = roomId; this.from = from; this.text = text;
    }

    @Override public void execute() {
        if (text == null || text.isBlank()) throw new IllegalArgumentException("Message cannot be empty.");
        server.getRoom(roomId).send(from, text);
    }
}

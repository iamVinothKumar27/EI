package chatapp;
public class PrivateMessageCommand implements Command {
    private final ChatServer server;
    private final String roomId;
    private final UserSession from;
    private final String toUsername;
    private final String text;

    public PrivateMessageCommand(ChatServer server, String roomId, UserSession from, String toUsername, String text) {
        this.server = server; this.roomId = roomId; this.from = from; this.toUsername = toUsername; this.text = text;
    }

    @Override public void execute() {
        if (text == null || text.isBlank()) throw new IllegalArgumentException("Message cannot be empty.");
        server.getRoom(roomId).sendPrivate(from, toUsername, text);
    }
}

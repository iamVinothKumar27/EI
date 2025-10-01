package chatapp;
public class JoinRoomCommand implements Command {
    private final ChatServer server;
    private final String roomId;
    private final UserSession user;

    public JoinRoomCommand(ChatServer server, String roomId, UserSession user) {
        this.server = server; this.roomId = roomId; this.user = user;
    }

    @Override public void execute() {
        ChatRoom room = server.getRoom(roomId);
        room.join(user);
    }
}

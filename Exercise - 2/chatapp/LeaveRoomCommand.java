package chatapp;
public class LeaveRoomCommand implements Command {
    private final ChatServer server;
    private final String roomId;
    private final UserSession user;

    public LeaveRoomCommand(ChatServer server, String roomId, UserSession user) {
        this.server = server; this.roomId = roomId; this.user = user;
    }

    @Override public void execute() {
        server.getRoom(roomId).leave(user);
    }
}

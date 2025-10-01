package chatapp;
public class CreateRoomCommand implements Command {
    private final ChatServer server;
    private final String roomId;

    public CreateRoomCommand(ChatServer server, String roomId) {
        this.server = server;
        this.roomId = roomId;
    }

    @Override public void execute() { server.createRoom(roomId); }
}

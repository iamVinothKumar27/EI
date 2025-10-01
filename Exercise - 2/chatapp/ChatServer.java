package chatapp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.*;
public class ChatServer {
    private static final Logger LOG = Logger.getLogger("ChatApp");
    private static final ChatServer INSTANCE = new ChatServer();

    private final Map<String, ChatRoom> rooms = new ConcurrentHashMap<>();

    private ChatServer() {}

    public static ChatServer getInstance() {
        return INSTANCE;
    }

    public synchronized void createRoom(String roomId) {
        validateRoomId(roomId);
        if (rooms.containsKey(roomId)) {
            throw new IllegalArgumentException("Room '" + roomId + "' already exists.");
        }
        rooms.put(roomId, new ChatRoom(roomId));
        LOG.info(() -> "Created room: " + roomId);
    }

    public ChatRoom getRoom(String roomId) {
        validateRoomId(roomId);
        ChatRoom room = rooms.get(roomId);
        if (room == null) throw new IllegalArgumentException("Room '" + roomId + "' not found.");
        return room;
    }

    public List<String> listRooms() {
        return rooms.keySet().stream().sorted().toList();
    }

    private void validateRoomId(String id) {
        if (id == null || id.isBlank() || !id.matches("[A-Za-z0-9_-]{1,32}")) {
            throw new IllegalArgumentException("Invalid room id. Use alphanumerics/[_-], max 32 chars.");
        }
    }
}

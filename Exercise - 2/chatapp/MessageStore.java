package chatapp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class MessageStore {
    private final Map<String, List<Message>> byRoom = new ConcurrentHashMap<>();

    public void add(String roomId, Message m) {
        byRoom.computeIfAbsent(roomId, k -> new ArrayList<>()).add(m);
    }

    public List<Message> get(String roomId) {
        return new ArrayList<>(byRoom.getOrDefault(roomId, List.of()));
    }
}

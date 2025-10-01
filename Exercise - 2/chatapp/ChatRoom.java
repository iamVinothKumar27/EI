package chatapp;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.*;
public class ChatRoom {
    private static final Logger LOG = Logger.getLogger("ChatApp");

    private final String roomId;
    private final List<ChatObserver> observers = new CopyOnWriteArrayList<>();
    private final MessageStore store = new MessageStore();

    ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() { return roomId; }

    public void join(UserSession user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        observers.add(user);
        user.setCurrentRoomId(roomId);
        broadcastSystem("** " + user.getUsername() + " joined **");
        LOG.info(() -> user.getUsername() + " joined " + roomId);
    }

    public void leave(UserSession user) {
        observers.remove(user);
        broadcastSystem("** " + user.getUsername() + " left **");
        LOG.info(() -> user.getUsername() + " left " + roomId);
    }

    public void send(UserSession from, String text) {
        if (from == null || text == null) return;
        Message msg = Message.of(from.getUsername(), text);
        store.add(roomId, msg);
        for (ChatObserver ob : observers) ob.onMessage(roomId, msg);
    }

    public void sendPrivate(UserSession from, String toUsername, String text) {
        Message msg = Message.privateOf(from.getUsername(), toUsername, text);
        store.add(roomId, msg);
        for (ChatObserver ob : observers) {
            if (ob instanceof UserSession us) {
                if (us.getUsername().equals(toUsername) || us.getUsername().equals(from.getUsername())) {
                    ob.onMessage(roomId, msg);
                }
            }
        }
    }

    public void broadcastSystem(String text) {
        Message sys = Message.system(text);
        store.add(roomId, sys);
        for (ChatObserver ob : observers) ob.onMessage(roomId, sys);
    }

    public List<Message> history() {
        return store.get(roomId);
    }

    public List<String> listUsernames() {
        List<String> names = new ArrayList<>();
        for (ChatObserver ob : observers) {
            if (ob instanceof UserSession us) names.add(us.getUsername());
        }
        Collections.sort(names);
        return names;
    }
}

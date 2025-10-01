import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private final String roomName;
    private final List<ChatObserver> observers = new ArrayList<>();

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public void join(ChatObserver observer) {
        observers.add(observer);
    }

    public void leave(ChatObserver observer) {
        observers.remove(observer);
    }

    public void post(String from, String message) {
        for (ChatObserver o : observers) {
            o.onMessage(from, message);
        }
    }

    public String getRoomName() {
        return roomName;
    }
}

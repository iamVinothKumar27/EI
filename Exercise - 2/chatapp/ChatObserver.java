package chatapp;
public interface ChatObserver {
    void onMessage(String roomId, Message message);
}

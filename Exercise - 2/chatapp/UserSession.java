package chatapp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class UserSession implements ChatObserver {
    private final String username;
    private final Transport transport;
    private String currentRoomId;
    private final BlockingQueue<String> inbox = new LinkedBlockingQueue<>();

    public UserSession(String username, Transport transport) {
        this.username = username;
        this.transport = transport;
        startInboxPrinter();
    }

    public String getUsername() { return username; }

    public String getCurrentRoomId() { return currentRoomId; }

    public void setCurrentRoomId(String id) { this.currentRoomId = id; }

    @Override
    public void onMessage(String roomId, Message message) {
        inbox.offer("[" + roomId + "] " + message.render());
    }

    private void startInboxPrinter() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    String msg = inbox.take();
                    transport.deliver(msg);
                }
            } catch (InterruptedException ignored) {}
        }, "inbox-" + username);
        t.setDaemon(true);
        t.start();
    }
}

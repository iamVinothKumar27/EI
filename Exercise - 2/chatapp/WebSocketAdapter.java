package chatapp;

/**
 * Demonstrates an Adapter for a hypothetical WebSocket client.
 * In this console app, we just simulate delivery via System.out.
 */
public class WebSocketAdapter implements Transport {
    @Override
    public void deliver(String payload) {
        // In a real app, this would push to a WebSocket session.
        System.out.println("[WS] " + payload);
    }
}

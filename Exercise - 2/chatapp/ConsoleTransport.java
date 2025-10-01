package chatapp;
public class ConsoleTransport implements Transport {
    @Override
    public void deliver(String payload) {
        System.out.println(payload);
    }
}

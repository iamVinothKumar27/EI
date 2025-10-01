public class BasePushNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[Push] " + message);
    }
}

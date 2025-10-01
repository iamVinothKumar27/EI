public class BaseEmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[Email] " + message);
    }
}

import java.time.LocalDateTime;

public class TimestampDecorator extends NotifierDecorator {
    public TimestampDecorator(Notifier inner) { super(inner); }
    @Override
    public void send(String message) {
        String ts = "[" + LocalDateTime.now().toString() + "] " + message;
        inner.send(ts);
    }
}

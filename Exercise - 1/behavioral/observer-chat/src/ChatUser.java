import java.util.ArrayList;
import java.util.List;

public class ChatUser implements ChatObserver {
    private final String name;
    private final List<String> inbox = new ArrayList<>();

    public ChatUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onMessage(String from, String message) {
        String line = "[" + name + " inbox] " + from + ": " + message;
        inbox.add(line);
        System.out.println(line);
    }
}

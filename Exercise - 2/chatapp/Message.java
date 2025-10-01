package chatapp;

import java.time.*;
import java.time.format.DateTimeFormatter;
public class Message {
    public final String from;
    public final String to; // null for public
    public final String text;
    public final boolean system;
    public final Instant ts;

    private Message(String from, String to, String text, boolean system, Instant ts) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.system = system;
        this.ts = ts;
    }

    public static Message of(String from, String text) {
        return new Message(from, null, text, false, Instant.now());
    }

    public static Message privateOf(String from, String to, String text) {
        return new Message(from, to, text, false, Instant.now());
    }

    public static Message system(String text) {
        return new Message("SYSTEM", null, text, true, Instant.now());
    }

    public String render() {
        String time = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault()).format(ts);
        if (system) return "[" + time + "] " + text;
        if (to != null) return "[" + time + "] (PM) " + from + " -> " + to + ": " + text;
        return "[" + time + "] " + from + ": " + text;
    }
}

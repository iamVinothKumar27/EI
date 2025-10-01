package chatapp;

import java.util.logging.*;
public class LoggerFactory {
    public static void configureRootLogger() {
        Logger root = Logger.getLogger("");
        for (var h : root.getHandlers()) root.removeHandler(h);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        handler.setFormatter(new SimpleFormatter());
        root.addHandler(handler);
        root.setLevel(Level.INFO);
    }
}

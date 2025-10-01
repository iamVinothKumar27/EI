import java.util.Map;
import java.util.Scanner;

public class SingletonConfigDemo {
    public static void runDemo(Scanner in) {
        AppConfig cfg = AppConfig.getInstance();
        System.out.println("Set config values. Type '/done' as key to stop.");
        while (true) {
            System.out.print("Key: ");
            String key = in.nextLine().trim();
            if (key.equalsIgnoreCase("/done")) break;
            System.out.print("Value: ");
            String value = in.nextLine().trim();
            cfg.set(key, value);
            System.out.println("Set: " + key + "=" + value);
        }
        System.out.println("Snapshot:");
        for (Map.Entry<String, String> e : cfg.snapshot().entrySet()) {
            System.out.println(" - " + e.getKey() + " = " + e.getValue());
        }
        System.out.println("Another component reads same instance: siteName=" + AppConfig.getInstance().get("siteName"));
    }
}

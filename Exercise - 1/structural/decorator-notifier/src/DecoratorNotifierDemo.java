import java.util.Scanner;

public class DecoratorNotifierDemo {
    public static void runDemo(Scanner in) {
        System.out.print("Enter a message to send: ");
        String msg = in.nextLine().trim();
        if (msg.isEmpty()) msg = "Hello from Decorator!";

        System.out.println("Choose base channel: 1) Email  2) SMS  3) Push");
        System.out.print("> ");
        String ch = in.nextLine().trim();
        Notifier notifier = switch (ch) {
            case "2" -> new BaseSMSNotifier();
            case "3" -> new BasePushNotifier();
            default -> new BaseEmailNotifier();
        };

        System.out.println("Add decorators? (y/n)");
        System.out.print("Timestamp? ");
        if (in.nextLine().trim().equalsIgnoreCase("y")) notifier = new TimestampDecorator(notifier);
        System.out.print("Encrypt? ");
        if (in.nextLine().trim().equalsIgnoreCase("y")) notifier = new EncryptDecorator(notifier);
        System.out.print("Compress? ");
        if (in.nextLine().trim().equalsIgnoreCase("y")) notifier = new CompressDecorator(notifier);

        System.out.println("\nSending...");
        notifier.send(msg);
    }
}

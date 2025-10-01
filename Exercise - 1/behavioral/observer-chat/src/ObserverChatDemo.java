import java.util.*;

public class ObserverChatDemo {
    public static void runDemo(Scanner in) {
        System.out.print("Enter room name: ");
        String roomName = in.nextLine().trim();
        ChatRoom room = new ChatRoom(roomName.isEmpty() ? "General" : roomName);

        System.out.print("Enter user names (comma separated): ");
        String[] names = in.nextLine().trim().split(",");
        List<ChatUser> users = new ArrayList<>();
        for (String n : names) {
            String s = n.trim();
            if (!s.isEmpty()) {
                ChatUser u = new ChatUser(s);
                room.join(u);
                users.add(u);
            }
        }
        if (users.isEmpty()) {
            ChatUser u = new ChatUser("Guest");
            room.join(u);
            users.add(u);
        }

        System.out.println("Type messages as 'UserName: message'. Type /exit to stop.");
        while (true) {
            System.out.print("> ");
            String line = in.nextLine();
            if (line.equalsIgnoreCase("/exit")) break;
            int idx = line.indexOf(":");
            if (idx <= 0) {
                System.out.println("Format: Name: message");
                continue;
            }
            String from = line.substring(0, idx).trim();
            String msg = line.substring(idx + 1).trim();
            room.post(from, msg);
        }
        System.out.println("Chat ended in room: " + room.getRoomName());
    }
}

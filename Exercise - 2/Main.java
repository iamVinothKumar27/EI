import chatapp.*;
import java.util.*;
import java.util.logging.*;

public class Main {
    private static final Logger LOG = Logger.getLogger("ChatApp");
    private static final Scanner SC = new Scanner(System.in);
    private static UserSession currentUser = null;

    public static void main(String[] args) {
        LoggerFactory.configureRootLogger();
        LOG.info("Starting Real-time Chat (Console) - Exercise 2");

        ChatServer server = ChatServer.getInstance();

        while (true) {
            try {
                if (currentUser == null) {
                    System.out.println("\n=== Welcome to Console Chat ===");
                    System.out.println("1) Login as user");
                    System.out.println("2) Create room");
                    System.out.println("3) Join room");
                    System.out.println("4) List rooms");
                    System.out.println("0) Exit");
                    System.out.print("Choose: ");
                    String choice = SC.nextLine().trim();

                    switch (choice) {
                        case "1":
                            loginUser(server);
                            break;
                        case "2":
                            createRoom(server);
                            break;
                        case "3":
                            joinRoom(server);
                            break;
                        case "4":
                            listRooms(server);
                            break;
                        case "0":
                            LOG.info("Exiting application.");
                            return;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } else {
                    userMenu(server);
                }
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, "Unexpected error", ex);
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void loginUser(ChatServer server) {
        System.out.print("Enter username: ");
        String name = SC.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }
        currentUser = new UserSession(name, new ConsoleTransport());
        System.out.println("Logged in as: " + name);
    }

    private static void createRoom(ChatServer server) {
        System.out.print("Enter new room ID (unique): ");
        String roomId = SC.nextLine().trim();
        try {
            new CreateRoomCommand(server, roomId).execute();
            System.out.println("Room created: " + roomId);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void joinRoom(ChatServer server) {
        System.out.print("Enter room ID to join: ");
        String roomId = SC.nextLine().trim();
        if (currentUser == null) {
            System.out.println("Please login first (option 1).");
            return;
        }
        try {
            new JoinRoomCommand(server, roomId, currentUser).execute();
            System.out.println("Joined room: " + roomId);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void listRooms(ChatServer server) {
        List<String> rooms = server.listRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms yet. Create one!");
            return;
        }
        System.out.println("Rooms: " + rooms);
    }

    private static void userMenu(ChatServer server) {
        System.out.println("\n=== User: " + currentUser.getUsername() + " ===");
        System.out.println("1) Join room");
        System.out.println("2) Send message to room");
        System.out.println("3) List active users");
        System.out.println("4) Send private message");
        System.out.println("5) Show message history");
        System.out.println("6) Leave room");
        System.out.println("7) Switch user (logout)");
        System.out.println("8) List rooms");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
        String choice = SC.nextLine().trim();
        switch (choice) {
            case "1":
                joinRoom(server);
                break;
            case "2":
                sendMessage(server);
                break;
            case "3":
                listActiveUsers(server);
                break;
            case "4":
                sendPrivateMessage(server);
                break;
            case "5":
                showHistory(server);
                break;
            case "6":
                leaveRoom(server);
                break;
            case "7":
                currentUser = null;
                System.out.println("Logged out.");
                break;
            case "8":
                listRooms(server);
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void sendMessage(ChatServer server) {
        if (!ensureInRoom(server)) return;
        System.out.print("Enter message: ");
        String text = SC.nextLine();
        new SendMessageCommand(server, currentUser.getCurrentRoomId(), currentUser, text).execute();
    }

    private static void listActiveUsers(ChatServer server) {
        if (!ensureInRoom(server)) return;
        List<String> users = server.getRoom(currentUser.getCurrentRoomId()).listUsernames();
        System.out.println("Active users: " + users);
    }

    private static void sendPrivateMessage(ChatServer server) {
        if (!ensureInRoom(server)) return;
        System.out.print("Enter target username: ");
        String target = SC.nextLine().trim();
        System.out.print("Enter message: ");
        String text = SC.nextLine();
        new PrivateMessageCommand(server, currentUser.getCurrentRoomId(), currentUser, target, text).execute();
    }

    private static void showHistory(ChatServer server) {
        if (!ensureInRoom(server)) return;
        new HistoryCommand(server, currentUser.getCurrentRoomId()).execute();
    }

    private static void leaveRoom(ChatServer server) {
        if (!ensureInRoom(server)) return;
        new LeaveRoomCommand(server, currentUser.getCurrentRoomId(), currentUser).execute();
        System.out.println("Left room: " + currentUser.getCurrentRoomId());
        currentUser.setCurrentRoomId(null);
    }

    private static boolean ensureInRoom(ChatServer server) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return false;
        }
        if (currentUser.getCurrentRoomId() == null) {
            System.out.println("You are not in a room. Join a room first.");
            return false;
        }
        return true;
    }
}

import io.socket.client.IO;
import io.socket.client.Socket;

import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws Exception {

        // Connect to the server
        Socket socket = IO.socket("http://localhost:3000");

        // Print messages sent by others
        socket.on("message", event -> {
            System.out.println("\n[Chat] " + event[0]);
        });

        socket.connect();
        System.out.println("Connected to ChatWire Java!");
        System.out.println("Type a message and press Enter.\n");

        Scanner scanner = new Scanner(System.in);

        // Main chat loop
        while (true) {
            System.out.print("You: ");
            String text = scanner.nextLine();

            if (text.trim().isEmpty()) continue;

            socket.emit("message", text);
        }
    }
}


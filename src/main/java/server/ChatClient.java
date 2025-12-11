package server;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {

        Socket socket = IO.socket("http://localhost:3000");

        socket.on("message", objects -> {
            System.out.println("\n[Chat] " + objects[0]);
        });

        socket.connect();
        System.out.println("Connected to ChatWire server! Type your message below:");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String msg = scanner.nextLine();
            if (!msg.trim().isEmpty()) {
                socket.emit("message", msg);
            }
        }
    }
}


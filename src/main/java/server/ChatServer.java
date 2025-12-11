package server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class ChatServer {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(3000);

        SocketIOServer server = new SocketIOServer(config);

        server.addEventListener("message", String.class, new DataListener<String>() {
            @Override
            public void onData(com.corundumstudio.socketio.SocketIOClient client,
                               String message,
                               com.corundumstudio.socketio.AckRequest ackRequest) {

                System.out.println("Received: " + message);
                server.getBroadcastOperations().sendEvent("message", message);
            }
        });

        server.start();
        System.out.println("ChatWire Java server running on port 3000");

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.stop();
    }
}


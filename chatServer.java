import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class ChatServer {

    public static void main(String[] args) {

        // Basic server setup
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(3000);

        SocketIOServer server = new SocketIOServer(config);

        // When a client sends "message", broadcast it
        server.addEventListener("message", String.class, new DataListener<String>() {
            @Override
            public void onData(com.corundumstudio.socketio.SocketIOClient client,
                               String message,
                               com.corundumstudio.socketio.AckRequest ackRequest) {

                System.out.println("User says: " + message);

                // Send to everyone connected
                server.getBroadcastOperations().sendEvent("message", message);
            }
        });

        server.start();
        System.out.println("ChatWire Java server running at http://localhost:3000");

        // Keep server alive
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.stop();
    }
}


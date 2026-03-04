package group.chatting.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class Server implements Runnable {

    Socket socket;
    public static Vector<BufferedWriter> client = new Vector<>();

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            client.add(writer);

            while (true) {
                String data = reader.readLine();
                if (data == null) continue;
                data = data.trim();

                System.out.println("Received: " + data);

                // Save message to MySQL
                saveMessageToDB(data);

                // Broadcast to all clients
                for (BufferedWriter bw : client) {
                    try {
                        bw.write(data);
                        bw.write("\r\n");
                        bw.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save message to MySQL database
    private void saveMessageToDB(String message) {
        String[] parts = message.split(":", 2); // format "username: message"
        if (parts.length < 2) return;

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO messages (sender, receiver, message) VALUES (?, ?, ?)")) {

            stmt.setString(1, parts[0].trim()); // sender
            stmt.setString(2, "all");           // receiver
            stmt.setString(3, parts[1].trim()); // message
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2003);
        System.out.println("Server started on port 2003...");

        while (true) {
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            Thread thread = new Thread(server);
            thread.start();
        }
    }
}

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerHashMap {
    public static void main(String[] args) throws IOException {
        int port = 6000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        // Files stored in HashMap (filename → content)
        HashMap<String, String> files = new HashMap<>();
        files.put("file1.txt", "Hello from File1 (HashMap)");
        files.put("file2.txt", "Java TCP Example (HashMap)");
        files.put("file3.txt", "Server File (HashMap)");

        boolean running = true;

        while (running) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Connected to HashMap Server. Commands: list, get <filename>, quit, stop");

            String command;
            while ((command = in.readLine()) != null) {
                if (command.equalsIgnoreCase("list")) {
                    String fileList = String.join(", ", files.keySet());
                    out.println("Available files: " + fileList);
                } else if (command.startsWith("get")) {
                    String[] parts = command.split(" ");
                    if (parts.length == 2) {
                        String fileName = parts[1];
                        if (files.containsKey(fileName)) {
                            out.println("Content: " + files.get(fileName));
                        } else {
                            out.println("File not found!");
                        }
                    } else {
                        out.println("Usage: get <filename>");
                    }
                } else if (command.equalsIgnoreCase("quit")) {
                    out.println("Closing connection...");
                    break;  // closes only client connection
                } else if (command.equalsIgnoreCase("stop")) {
                    out.println("Shutting down server...");
                    running = false; // break outer loop
                    break;
                } else {
                    out.println("Invalid command!");
                }
            }
            socket.close();
        }

        serverSocket.close();
        System.out.println("Server stopped.");
    }
}

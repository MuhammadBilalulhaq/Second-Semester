import java.io.*;
import java.net.*;

public class ServerArray {
    public static void main(String[] args) throws IOException {
        int port = 5000; // server port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        // Simulated files stored in array
        String[] files = {
            "file1.txt: Hello from File1 (Array)",
            "file2.txt: Java TCP Example (Array)",
            "file3.txt: Server File (Array)"
        };

        boolean running = true;

        while (running) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Connected to Array Server. Commands: list, get <filename>, quit, stop");

            String command;
            while ((command = in.readLine()) != null) {
                if (command.equalsIgnoreCase("list")) {
                    // Build one single string of filenames
                    StringBuilder fileList = new StringBuilder();
                    for (String file : files) {
                        fileList.append(file.split(":")[0]).append(", ");
                    }
                    // Remove last comma + space
                    if (fileList.length() > 0) {
                        fileList.setLength(fileList.length() - 2);
                    }
                    out.println("Available files: " + fileList.toString());
                } else if (command.startsWith("get")) {
                    String[] parts = command.split(" ");
                    if (parts.length == 2) {
                        String fileName = parts[1];
                        boolean found = false;
                        for (String file : files) {
                            if (file.startsWith(fileName)) {
                                out.println("Content: " + file.split(":", 2)[1]);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            out.println("File not found!");
                        }
                    } else {
                        out.println("Usage: get <filename>");
                    }
                } else if (command.equalsIgnoreCase("quit")) {
                    out.println("Closing connection...");
                    break; // closes client only
                } else if (command.equalsIgnoreCase("stop")) {
                    out.println("Shutting down server...");
                    running = false; // stop outer loop
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

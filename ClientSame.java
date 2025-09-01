import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSame {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter server IP: ");
        String serverIP = sc.nextLine();
        System.out.print("Enter server port: ");
        int port = sc.nextInt();
        sc.nextLine(); // consume newline

        Socket socket = new Socket(serverIP, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Server says: " + in.readLine());

        String command;
        while (true) {
            System.out.print("Client> ");
            command = sc.nextLine();
            out.println(command);

            if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("stop")) {
                System.out.println("Closing connection...");
                break;
            }

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Server> " + response);
                break; // only read one line response
            }
        }
        socket.close();
        sc.close();
    }
}

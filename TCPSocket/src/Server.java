import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is waiting to accept user....");
            Socket socket = serverSocket.accept();
            System.out.println("Accept a client!");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = input.readLine();
                if (line.equals("exit")) {
                    break;
                }
                output.println("Messeage from server: " + line);
            }
        }catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String sentence;
            String response;
            do {
                System.out.println("Please enter your message: ");
                sentence = scanner.nextLine();
                outputToServer.println(sentence);
                if (!sentence.equals("exit")) {
                    response = inputFromServer.readLine();
                    System.out.println(response);
                }
            } while (!sentence.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = 120;  // Port based on the last 3 digits of the student ID

        Socket socket = new Socket("localhost", port);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send initial message to the server
        outToServer.writeBytes("Hello, My ID is 2001040210, my is Ngo Thi Huyen Trang " + "\n");
        System.out.println("Enter a String: ");
        String sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + "\n");
        for (int i = 0; i < 2; i++) {
            String modifiedSentence = inFromServer.readLine();
            System.out.println("From server: " + modifiedSentence);
        }
    }
}

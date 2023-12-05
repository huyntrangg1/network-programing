import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
            int port = 120;  // Port based on the last 3 digits of the student ID
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server is listening on port " + port);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ServerThread serverThread = new ServerThread(clientSocket);
                    serverThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            BufferedReader inFromClient = null;
            try {
                 inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            DataOutputStream outToClient = null;
            try {
                 outToClient = new DataOutputStream(socket.getOutputStream());
            }catch (IOException e1) {
                e1.printStackTrace();
            }

            String sentence = null;
            try {
                sentence = inFromClient.readLine();
            }catch (IOException e) {
                e.printStackTrace();
            }
            String modify = sentence.toUpperCase();
            try {
                outToClient.writeBytes(modify + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
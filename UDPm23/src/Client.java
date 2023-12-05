import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = 12110;  // Port based on the last 3 digits of the student ID

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        // Step 2: Send Student_ID and receive the first reply
        String studentID = "2001040210";
        sendData(studentID.getBytes(), clientSocket, serverAddress, port);
        String reply1 = receiveData(clientSocket);
        System.out.println("Received from server: " + reply1);

        // Step 3: Loop to handle further messages
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Enter a message: ");
            String message = inFromUser.readLine();
            sendData(message.getBytes(), clientSocket, serverAddress, port);

            String serverResponse = receiveData(clientSocket);
            System.out.println("Received from server: " + serverResponse);
        }
    }

    private static void sendData(byte[] data, DatagramSocket clientSocket, InetAddress serverAddress, int port) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, serverAddress, port);
        clientSocket.send(sendPacket);
    }

    private static String receiveData(DatagramSocket clientSocket) throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}

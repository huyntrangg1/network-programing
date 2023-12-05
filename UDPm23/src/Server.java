
import java.math.BigInteger;
 import java.io.IOException;
 import java.net.DatagramPacket;
 import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 12110;  // Port based on the last 3 digits of the student ID

        DatagramSocket serverSocket = new DatagramSocket(port);
        System.out.println("Server is listening on port " + port);

        byte[] receiveData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Process the first message
            BigInteger studentIDValue = new BigInteger(clientMessage);
            BigInteger result = studentIDValue.multiply(BigInteger.valueOf(2));
            sendData(result.toString().getBytes(), receivePacket);

            // Process subsequent messages in a loop
            while (true) {
                serverSocket.receive(receivePacket);
                clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (isPositiveInteger(clientMessage)) {
                    BigInteger num = new BigInteger(clientMessage);
                    BigInteger square = num.pow(2);
                    sendData(square.toString().getBytes(), receivePacket);
                } else {
                    sendData(clientMessage.getBytes(), receivePacket);
                }
            }
        }
    }

    private static void sendData(byte[] data, DatagramPacket receivePacket) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(sendPacket);
        serverSocket.close();
    }

    private static boolean isPositiveInteger(String str) {
        try {
            BigInteger num = new BigInteger(str);
            return num.compareTo(BigInteger.ZERO) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

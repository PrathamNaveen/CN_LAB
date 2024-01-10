import java.net.*;
import java.util.Scanner;

class Server {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        DatagramSocket dgSocket = new DatagramSocket();

        InetAddress address = InetAddress.getByName("127.0.0.1");
        String message;
        byte[] buffer;
        DatagramPacket dgPacket;
        
        System.out.println("Enter messages to send: ");
        while (true) {
            message = sc.nextLine();
            buffer = message.getBytes();

            dgPacket = new DatagramPacket(buffer, buffer.length, address, 4000);
            dgSocket.send(dgPacket);

            if (message.equalsIgnoreCase("exit")) {
                dgSocket.close();
                break;
            }

        }
    }

}
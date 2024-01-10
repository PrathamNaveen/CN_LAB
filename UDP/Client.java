import java.net.*;

class Client {
    public static void main(String args[]) throws Exception {
        DatagramSocket dgSocket = new DatagramSocket(4000);
        byte[] buffer;
        DatagramPacket dgPacket;
        System.out.println("Received Messages: ");
        while (true) {
            buffer = new byte[65555];
            dgPacket = new DatagramPacket(buffer, buffer.length);

            dgSocket.receive(dgPacket);
            String received = new String(buffer).trim();
            System.out.println(received);
            
            if (received.equalsIgnoreCase("exit")) {
                dgSocket.close();
                break;
            }
        }
    }
}
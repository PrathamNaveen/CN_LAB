// Program to implement Random Early Detection(RED) in Java

public class Main {
    public static void main(String args[]) {
        final int NO_OF_PACKETS = 20;
        final int QUE_SIZE = 10;

        final double MAX_PRO = 0.7;
        final double MIN_PRO = 0.3;

        int queLen = 0;
        double dropPro = MIN_PRO;
        for (int i = 0; i < NO_OF_PACKETS; i++) {
            if (queLen == QUE_SIZE) {
                System.out.println("Packet dropped ( Queue Full ) ");
                dropPro = MIN_PRO;
            } else if (Math.random() < dropPro) {
                System.out.println("Packet dropped ( Random ) ");
                dropPro += (MAX_PRO - MIN_PRO) / (NO_OF_PACKETS - 1);
            } else {
                System.out.println("Packet Accepted");
                queLen++;
                dropPro = MIN_PRO;
            }
        }

    }
}
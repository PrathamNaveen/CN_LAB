// Program to Implement Token Bucket Algorithm for Congestion Control using Java

import java.util.Random;
import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        int tokens = 0, count = 0, n;
        final int rate = 2, bCapacity = 5;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the Number of Packets: ");
        n = sc.nextInt();
        int packets[] = new int[n];
        for (int i=0;i<n;i++){
            packets[i] = rand.nextInt(1, bCapacity);
            System.out.println("Packet "+(i+1)+": "+packets[i]);
        }

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            if (tokens + rate <= bCapacity)
                tokens += rate;

            for (int i=0;i<n;i++) {
                if (packets[i] != 0 && packets[i] <= tokens){
                    tokens -= packets[i];
                    System.out.println("Request Granted for Packet: "+(i + 1)+", Tokens Remaining: " + tokens);
                    packets[i] = 0;
                    count++;
                    if (count == n)
                        return;
                }
            }
            System.out.println("Request Denied, Tokens Remaining: " + tokens);
        }
    }
}

// Program to Implement Leaky Bucket Algorithm for Congestion Control using Java

import java.util.Scanner;

class Main{
    public static void main(String args[]){
        int n, bCapacity, outRate, filled=0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of Packets: ");
        n = sc.nextInt();

        System.out.print("\nEnter the Bucket Size: ");
        bCapacity = sc.nextInt();

        System.out.print("\nEnter the Output Rate: ");
        outRate = sc.nextInt();

        int packets[] = new int[n];
        System.out.println("Enter the value of each Packet: ");
        for (int i=0;i<n;i++)
            packets[i] = sc.nextInt();
            
        for (int packet : packets){
            if (packet + filled > bCapacity){
                System.out.println("Bucket Capacity Exceeded");
                continue;
            }
            else{
                filled += packet;

                if (filled != 0){
                    if (filled <= outRate){
                        System.out.println("Data sent: "+filled);
                        filled = 0;
                    }
                    else{
                        filled -= outRate;
                        System.out.println("Data sent: "+outRate);
                    }
                }
            }
        }
    }
}
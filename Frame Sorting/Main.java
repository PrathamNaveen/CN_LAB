// Program to Sort Frames using Bubble Sort

import java.util.Scanner;
import java.util.Random;

class Frame{
    int frameNo, data;
}

class Main{

    static void bubbleSort(Frame frames[], int n){
        for(int i=0;i<n-1;i++){
            boolean swaps = false;
            for(int j=0;j<n-i-1;j++){
                if (frames[j].frameNo > frames[j+1].frameNo){
                    swaps = true;
                    Frame temp = frames[j]; 
                    frames[j] = frames[j+1];
                    frames[j+1] = temp;
                }
            }
            if (!swaps)
                break; 
        }
    }

    static void display(Frame frames[]){
        System.out.println("Frame Number : Frame Data");
        for(Frame frame : frames)
            System.out.println(frame.frameNo+" : "+frame.data);
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the Number of Frames:");
        int n = sc.nextInt();
        Frame frames[] = new Frame[n];
        System.out.println("Enter the data for each Frame");
        for (int i = 0; i < n; i++) {
            frames[i] = new Frame();
            frames[i].data = sc.nextInt();
            frames[i].frameNo = rand.nextInt(n); // 0 to n-1
        }
        System.out.println("Before Sorting: ");
        display(frames);
        bubbleSort(frames, n);
        System.out.println("After Sorting: ");
        display(frames);
    }
}
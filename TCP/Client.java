// Client Side to print the contents of the file

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("127.0.0.1", 4000);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Filename: ");
        String fName = sc.next();

        OutputStream oStream = socket.getOutputStream();
        PrintWriter pWrite = new PrintWriter(oStream, true);
        pWrite.println(fName);

        InputStream iStream = socket.getInputStream();
        sc = new Scanner(iStream);
        
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }
}
// Server side to send the contents of the file to the client

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server {
    public static void main(String args[]) throws Exception {
        ServerSocket servSocket = new ServerSocket(4000);
        System.out.println("Server Connected, Waiting for Client");

        Socket socket = servSocket.accept();
        System.out.println("Connection Successfull, Waiting for filename ...");

        InputStream iStream = socket.getInputStream();
        Scanner sc = new Scanner(iStream);

        String fName = sc.nextLine();
        OutputStream oStream = socket.getOutputStream();
        PrintWriter pWrite = new PrintWriter(oStream, true);
        
        try {
            sc = new Scanner(new File(fName));
            while (sc.hasNextLine()) {
                pWrite.println(sc.nextLine());
            }
        } catch (Exception e) {
            pWrite.println("File name does not exists");
        } finally {
            System.out.println("Closing Connection");
        }
    }
}
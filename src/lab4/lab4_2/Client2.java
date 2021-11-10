package lab4.lab4_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5001);
            if (socket.isConnected()) {
                System.out.println("Connection complete");
            }
            while (true) {
                DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                String sendToServer = scanner.nextLine();
                outToServer.writeUTF(sendToServer);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}

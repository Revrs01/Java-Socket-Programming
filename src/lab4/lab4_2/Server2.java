package lab4.lab4_2;

import java.io.*;
import java.net.*;

class Server2 {
    static class MultipleClients extends Thread {
        private final Socket server;
        private int clientNo;

        MultipleClients(Socket socket, int counter) throws IOException {
            this.server = socket;
            clientNo = counter;

        }




        public void run() {
            String string;
            try {
                DataInputStream inputFromClient = new DataInputStream(server.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(server.getOutputStream());
                while (true) {

                    if (clientNo > 2){
                        System.out.println();
                        outToClient.writeUTF("Server is FULL");
                        //clientNo--;
                        break;
                    }else {
                        outToClient.writeUTF("");
                    }
                    System.out.println(clientNo + " User now");
                    string = inputFromClient.readUTF();
                    System.out.println("Client" + clientNo + " says " + string);

                    if (string.equals("*Close*")) {
                        System.out.println("Client Disconnected");
                        clientNo--;
                        break;
                    }
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5001);
        int counter = 0;
        while (true) {
            counter++;
            Socket socket = serverSocket.accept();
            MultipleClients multipleClients = new MultipleClients(socket, counter);
            multipleClients.start();

        }

    }
}

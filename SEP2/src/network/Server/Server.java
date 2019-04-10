package network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int PORT = 5678;

    public static void main(String[] args) throws IOException {

        ServerSocket welcomeSocket = new ServerSocket(PORT);
        System.out.println("Starting server...");

        while (true) {
            Socket socket = welcomeSocket.accept();
            ServerReceiver serverReceiver = new ServerReceiver(socket);
            Thread t1 = new Thread(serverReceiver);
            t1.start();
        }
    }
}

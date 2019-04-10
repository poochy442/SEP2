package network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket welcomeSocket;
    private ServerReceiver serverReceiver;


    public Server(int PORT) throws IOException {
        welcomeSocket = new ServerSocket(PORT);
        System.out.println("Starting server...");
    }

    public void execute() throws IOException {
        while (true) {
            Socket socket = welcomeSocket.accept();
            serverReceiver = new ServerReceiver(socket);
            Thread t1 = new Thread(serverReceiver);
            t1.start();
        }
    }
}

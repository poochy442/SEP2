package network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket welcomeSocket;
    private ServerReceiver serverReceiver;
//    private Model model;


    public Server(int PORT) throws IOException {

        welcomeSocket = new ServerSocket(PORT);

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

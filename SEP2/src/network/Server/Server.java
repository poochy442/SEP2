package network.Server;

import jdbc.DataBaseModel;

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
            DataBaseModel dataBaseModel = new DataBaseModel();
            ServerReceiver serverReceiver = new ServerReceiver(socket,dataBaseModel);
            ServerSender serverSender = new ServerSender(socket,dataBaseModel);
            Thread t2 = new Thread(serverSender);
            Thread t1 = new Thread(serverReceiver);
            t1.start();
            t2.start();
        }
    }
}

package network.Server;

import jdbc.DataBaseModel;
import model.DataModel;
import model.IDataModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int PORT = 5678;

    public static void main(String[] args) throws IOException {

        ServerSocket welcomeSocket = new ServerSocket(PORT);
        System.out.println("Starting server...");
        IDataModel dataModel = new DataModel();
        DataBaseModel dataBaseModel = new DataBaseModel();

        while (true) {
            Socket socket = welcomeSocket.accept();
            ServerReceiver serverReceiver = new ServerReceiver(socket,dataBaseModel,dataModel);
            ServerSender serverSender = new ServerSender(socket,dataBaseModel);
            Thread t1 = new Thread(serverReceiver);
            Thread t2 = new Thread(serverSender);

            t1.start();
            t2.start();
        }
    }
}

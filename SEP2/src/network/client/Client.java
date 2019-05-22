package network.client;

import model.IDataModel;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    private final int PORT = 5678;
    private String HOST;
    private IDataModel dataModel;

    public Client(String HOST, IDataModel dataModel) {
        this.HOST = HOST;
        this.dataModel = dataModel;
    }

    @Override
    public void run() {


        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClientSender clientSender = new ClientSender(socket, dataModel);
        Thread t1 = new Thread(clientSender);
        t1.start();
        dataModel.loadEmployeeListFromDB();
        dataModel.loadItemListFromDB();
        System.out.println("Client Refresh employee list");
        ClientReceiver clientReceiver = new ClientReceiver(socket, dataModel);
        Thread t2 = new Thread(clientReceiver);
        t2.start();
    }
}

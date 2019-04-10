package network.client;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

    private final int PORT = 5678;
    private String HOST;

    public Client(String HOST)
    {
        this.HOST = HOST;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread client = new Thread(new ClientSender(socket));
        client.start();

        // TODO: start view
    }
}

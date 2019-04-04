package network.client;

import java.io.ObjectOutputStream;



public class ClientSender implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private Client client;

    public ClientSender(ObjectOutputStream objectOutputStream, Client client) {
        this.client = client;
        this.objectOutputStream = objectOutputStream;
    }



    @Override
    public void run() {


    }
}

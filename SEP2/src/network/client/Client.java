package network.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements IClient{

//    private Model model;

    private ClientSender clientSender;

    public Client ()
    {
//        this.model = model;


            try {
                Socket socket = new Socket("localhost", 1234);
                ClientSender clientSender = new ClientSender(new ObjectOutputStream(socket.getOutputStream()), this);

                Thread t = new Thread(clientSender);
                t.start();
                System.out.println("Sending Thread Openning");
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
}

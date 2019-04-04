package network.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReceiver implements Runnable {
    private Socket socket;
    private ObjectInputStream objectInputStream;

    public ServerReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true)
            {
              objectInputStream.readObject();




            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

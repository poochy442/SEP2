package network.Server;

import jdbc.DataBaseModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <h1>Server Class responsible for opening a connection and accepting clients</h1>
 * The {@link Socket}s of the clients are passed to a {@link ServerReceiver} for incoming traffic
 * and a {@link ServerSender} for outgoing traffic
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class Server {

    private static int PORT = 5678;

    /**
     * The main method starting and running a {@link ServerSocket} for clients to connect to
     * Then starts a Thread with a {@link ServerSender} and a Thread with a {@link ServerReceiver}
     * @param args The command line arguments passed
     * @throws IOException This method throws an IOException if it is unsuccessful in setting up the
     * {@link ServerSocket}
     */
    public static void main(String[] args) throws IOException {

            ServerSocket welcomeSocket = new ServerSocket(PORT);
        System.out.println("Starting server...");
        DataBaseModel dataBaseModel = new DataBaseModel();
        int i=1;

        while (true) {
            Socket socket = welcomeSocket.accept();

            ServerReceiver serverReceiver = new ServerReceiver(socket,dataBaseModel,i);
            ServerSender serverSender = new ServerSender(socket,dataBaseModel,i);
            Thread t2 = new Thread(serverSender);
            Thread t1 = new Thread(serverReceiver);
            t1.start();
            t2.start();
            i++;
        }
    }
}

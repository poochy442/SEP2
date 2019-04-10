package network.client;

public class ClientStarter {
    public static void main(String[] args) {
        Thread t = new Thread(new Client("localhost"));
        t.start();
    }
}

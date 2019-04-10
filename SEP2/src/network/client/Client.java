package network.client;

import com.google.gson.Gson;
import model.Employee;
import model.EmployeeList;
import network.Packet;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private final int PORT = 5678;
    private String HOST;

    public Client(String HOST) {
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

        ClientSender client = new ClientSender(socket);

        Thread t1 = new Thread(client);
        t1.start();

        // TODO: start view, instead of scanner

        Scanner input = new Scanner(System.in);

        System.out.println("Insert 3 names:");

        EmployeeList empList = new EmployeeList();
        for(int i = 0; i < 3; i++){
            empList.add(new Employee(input.next(), "lastname", "" + i));
        }

        Gson gson = new Gson();
        String json = gson.toJson(empList);

        Packet packet = new Packet(Packet.EmployeeOperation, json);
        client.addToQueue(packet);

    }
}

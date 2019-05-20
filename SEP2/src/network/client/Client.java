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

        ClientSender client = new ClientSender(socket, dataModel);
        ClientReceiver clientReceiver = new ClientReceiver(socket,dataModel);

        Thread t1 = new Thread(client);
        Thread t2 = new Thread(clientReceiver);
        t1.start();
        t2.start();

        // TODO: start view, instead of scanner

//        Scanner input = new Scanner(System.in);
//
//        System.out.println("Insert 3 names:");
//
//        EmployeeList empList = new EmployeeList();
//        for(int i = 0; i < 3; i++){
//            empList.add(new Employee(input.next(), "lastname", "" + i));
//        }
//
//        Gson gson = new Gson();
//        String json = gson.toJson(empList);
//
//        Packet packet = new Packet(Packet.EmployeeOperation, json);
//        client.addToQueue(packet);

    }
}

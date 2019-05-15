package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.EmployeeList;
import network.Packet;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ServerSender implements Runnable {

    private Socket socket;
    private Queue<Packet> queue;
    private DataBaseModel dataBaseModel;

    public ServerSender(Socket socket,DataBaseModel dataBaseModel) {
        this.socket = socket;
        this.dataBaseModel=dataBaseModel;
        queue = new LinkedList<>();
        dataBaseModel.addListener("EmployeeQuery",this::employeeListQueryListener);
    }

    private void employeeListQueryListener(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        EmployeeList employeeList =((EmployeeList) propertyChangeEvent.getNewValue());
        String json = gson.toJson(employeeList);
        Packet packet = new Packet(Packet.EmployeeQuery, json);
        addToQueue(packet);
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            while(queue.isEmpty()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                out.writeObject(queue.poll());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addToQueue(Packet packet){
        queue.add(packet);
    }


}

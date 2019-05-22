package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.EmployeeList;
import model.StockItemList;
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

    public ServerSender(Socket socket, DataBaseModel dataBaseModel) {
        this.socket = socket;
        this.dataBaseModel=dataBaseModel;
        queue= new LinkedList<>();
        dataBaseModel.addListener("EmployeeQuery",this::sendEmployeeList);
        dataBaseModel.addListener("ItemQuery",this::sendItemList);
        // TODO: Add listener for the Response
    }

    private void sendItemList(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        StockItemList stockItemList =((StockItemList) propertyChangeEvent.getNewValue());
        String json = gson.toJson(stockItemList);
        Packet packet = new Packet(Packet.ItemQuery, json);
        addToQueue(packet);
        for (int i=0;i<stockItemList.size();i++)
        {
            System.out.println(stockItemList.get(i).getName());
        }
        System.out.println("ServerSender: StockItemListPacket sent");
    }

    private void sendEmployeeList(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        EmployeeList employeeList =((EmployeeList) propertyChangeEvent.getNewValue());
        String json = gson.toJson(employeeList);
        Packet packet = new Packet(Packet.EmployeeQuery, json);
        addToQueue(packet);
        System.out.println("ServerSender: EmployeListPacket sent");
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
            if(queue.isEmpty()){
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

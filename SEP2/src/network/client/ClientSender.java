package network.client;

import com.google.gson.Gson;
import model.*;
import network.Packet;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ClientSender implements Runnable {

    private Socket socket;
    private Queue<Packet> queue;
    private IDataModel dataModel;

    public ClientSender(Socket socket, IDataModel dataModel) {
        this.socket = socket;
        this.dataModel = dataModel;
        dataModel.addListener("NewEmployeeFromUser", this::addEmployeeListener);
        dataModel.addListener("NewItemFromUser", this::addStockItemListener);
        dataModel.addListener("EmployeeQuery",this::triggerEmployeeQuery);
       dataModel.addListener("ItemQuery",this::triggerItemQuery);
       dataModel.addListener("SendProductRequest",this::addRequestListener);
       dataModel.addListener("DeleteItemFromWH",this::deleteItemFromWH);
        queue = new LinkedList<>();
    }

    private void deleteItemFromWH(PropertyChangeEvent propertyChangeEvent) {
        StockItem stockItem = (StockItem) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(stockItem);
        Packet p1 = new Packet(Packet.DeleteItemFromWH,json);
        addToQueue(p1);
    }

    private void triggerItemQuery(PropertyChangeEvent propertyChangeEvent) {
        Packet p = new Packet(Packet.ItemQuery,null);
        System.out.println("ClientSenderTriggerItemQuery");
        addToQueue(p);
    }

    private void triggerEmployeeQuery(PropertyChangeEvent propertyChangeEvent) {
        Packet p = new Packet(Packet.EmployeeQuery,null);
        System.out.println("ClientSenderEmployeeItemQuery");
        addToQueue(p);
    }

    private void addStockItemListener(PropertyChangeEvent propertyChangeEvent) {
        StockItemList stockItemList = new StockItemList();
        stockItemList.add((StockItem) propertyChangeEvent.getNewValue());
        Gson gson = new Gson();
        String json = gson.toJson(stockItemList);
        Packet packet = new Packet(Packet.StockOperation, json);
        System.out.println("ClientSender: addStockItemListener StockItemOperation");
        addToQueue(packet);
    }

    private void addEmployeeListener(PropertyChangeEvent propertyChangeEvent) {
        Employee employe =(Employee)propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(employe);
        Packet packet = new Packet(Packet.EmployeeOperation, json);
        System.out.println("ClientSender: addEmployeListener EmployeeOperation");
        addToQueue(packet);

    }

    public void addRequestListener(PropertyChangeEvent propertyChangeEvent){
        ProductRequestList productRequestList = (ProductRequestList) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(productRequestList);
        Packet packet = new Packet(Packet.RequestOperation, json);
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

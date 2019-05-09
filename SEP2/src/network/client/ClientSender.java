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
        dataModel.addListener("NewEmployeeAddedFromClient", this::addEmployeeListener);
        dataModel.addListener("NewItemAddedFromClient", this::addStockItemListener);
        // TODO: Add Request Listener
        queue = new LinkedList<>();
    }

    private void addStockItemListener(PropertyChangeEvent propertyChangeEvent) {
        StockItemList stockItemList = new StockItemList();
        stockItemList.add((StockItem) propertyChangeEvent.getNewValue());
        Gson gson = new Gson();
        String json = gson.toJson(stockItemList);
        Packet packet = new Packet(Packet.StockOperation, json);
        addToQueue(packet);
    }

    private void addEmployeeListener(PropertyChangeEvent propertyChangeEvent) {
        EmployeeList employeeList = new EmployeeList();
        employeeList.add((Employee) propertyChangeEvent.getNewValue());
        Gson gson = new Gson();
        String json = gson.toJson(employeeList);
        Packet packet = new Packet(Packet.EmployeeOperation, json);
        addToQueue(packet);
    }

    public void addRequestListener(PropertyChangeEvent propertyChangeEvent){
        Request request = (Request) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(request);
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
                    Thread.sleep(5000);
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

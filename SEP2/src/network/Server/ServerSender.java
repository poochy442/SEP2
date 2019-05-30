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

/**
 * <h1>ServerSender Class responsible for the {@link Server}s outgoing traffic</h1>
 * The ServerSocket implements a modified Producer/Consumer pattern with the {@link network.client.ClientReceiver}
 * as the Consumer.
 * It stores a {@link Queue} and methods to add to it, and then sends the {@link Packet}s contained in the {@link Queue}
 * to the {@link network.client.ClientReceiver}.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ServerSender implements Runnable {

    private Socket socket;
    private Queue<Packet> queue;
    private DataBaseModel dataBaseModel;

    /**
     * Creates ServerSender with the specified information
     * @param socket The {@link Socket} to be used.
     * @param dataBaseModel The {@link DataBaseModel} to  be used.
     */
    public ServerSender(Socket socket, DataBaseModel dataBaseModel) {
        this.socket = socket;
        this.dataBaseModel=dataBaseModel;
        queue= new LinkedList<>();
        dataBaseModel.addListener("EmployeeQuery",this::sendEmployeeList);
        dataBaseModel.addListener("ItemQuery",this::sendItemList);
        dataBaseModel.addListener("SalesQuery",this::sendSalesList);
        // TODO: Add listener for the Response
    }

    private void sendSalesList(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        StockItemList stockItemList =((StockItemList) propertyChangeEvent.getNewValue());
        String json = gson.toJson(stockItemList);
        Packet packet = new Packet(Packet.salesQuery, json);
        addToQueue(packet);
        System.out.println("ServerSender: Sales List sent");

    }

    /**
     * Sends a {@link StockItemList} to the client.
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void sendItemList(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        StockItemList stockItemList =((StockItemList) propertyChangeEvent.getNewValue());
        String json = gson.toJson(stockItemList);
        Packet packet = new Packet(Packet.ItemQuery, json);
        addToQueue(packet);
        System.out.println("ServerSender: StockItemListPacket sent");
    }

    /**
     * Sends a {@link EmployeeList} to the client.
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void sendEmployeeList(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        EmployeeList employeeList =((EmployeeList) propertyChangeEvent.getNewValue());
        String json = gson.toJson(employeeList);
        Packet packet = new Packet(Packet.EmployeeQuery, json);
        addToQueue(packet);
        System.out.println("ServerSender: EmployeListPacket sent");
    }

    /**
     * The run method inherited from {@link Runnable}.
     * The method loops running {@link Thread#sleep(long)} and checking if something is in the {@link Queue}.
     * If there is something in the {@link Queue}, it will send it to the {@link network.client.ClientReceiver}.
     */
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

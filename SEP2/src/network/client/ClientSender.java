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

/**
 * The ClientSender is the Class responsible for the outgoing traffic of the {@link Client}.
 * The ClientSender implements a modified Producer/Consumer pattern with the {@link network.Server.ServerReceiver}
 * as the Consumer. It stores a {@link Queue} and a method to add {@link Packet}s to the {@link Queue},
 * which it will then send. When there is nothing in the {@link Queue}, it will {@link Thread#sleep(long)}.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ClientSender implements Runnable {

    private Socket socket;
    private Queue<Packet> queue;
    private IDataModel dataModel;

    /**
     * Creates a ClientSender with the specified information and adds the required {@link java.net.http.WebSocket.Listener}s.
     *
     * @param socket    The {@link Socket} for the ClientSender to use.
     * @param dataModel The {@link DataModel} for the ClientSender to use.
     */
    public ClientSender(Socket socket, IDataModel dataModel) {
        this.socket = socket;
        this.dataModel = dataModel;
        dataModel.addListener("NewEmployeeFromUser", this::addEmployeeListener);
        dataModel.addListener("NewItemFromUser", this::addStockItemListener);
        dataModel.addListener("EmployeeQuery", this::triggerEmployeeQuery);
        dataModel.addListener("ItemQuery", this::triggerItemQuery);
        dataModel.addListener("SendProductRequest", this::addRequestListener);
        dataModel.addListener("DeleteItemFromWH", this::deleteItemFromWH);
        dataModel.addListener("DeleteEmployee", this::deleteEmployee);
        dataModel.addListener("AddSale", this::addSale);
        dataModel.addListener("SalesQuery", this::salesQuery);
        dataModel.addListener("AddProductRequest", this::addRequest);
        queue = new LinkedList<>();
    }

    private void addRequest(PropertyChangeEvent propertyChangeEvent) {
        ProductRequest productRequest = (ProductRequest) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(productRequest);
        Packet packet = new Packet(Packet.addProductRequest, json);
        addToQueue(packet);
        System.out.println("ClientSender: Request Packet Sent");
    }

    private void salesQuery(PropertyChangeEvent propertyChangeEvent) {
        Packet p1 = new Packet(Packet.salesQuery, "");
        addToQueue(p1);
        System.out.println("ClientSender: SalesQuery");
    }

    private void addSale(PropertyChangeEvent propertyChangeEvent) {
        StockItem saleItem = (StockItem) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(saleItem);
        Packet p1 = new Packet(Packet.AddSale, json);
        System.out.println("ClientSender: " + saleItem.getId() + " sent to server");
        addToQueue(p1);
    }

    private void deleteEmployee(PropertyChangeEvent propertyChangeEvent) {

        Employee employee = (Employee) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        Packet p1 = new Packet(Packet.DeleteEmployee, json);
        addToQueue(p1);

    }

    /**
     * Deletes a {@link StockItem} from the Warehouse.
     *
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that triggered this method to be run.
     */
    private void deleteItemFromWH(PropertyChangeEvent propertyChangeEvent) {
        StockItem stockItem = (StockItem) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(stockItem);
        Packet p1 = new Packet(Packet.DeleteItemFromWH, json);
        addToQueue(p1);
    }

    /**
     * Adds an Item Request to the {@link Queue}.
     *
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that triggered this method to be run.
     */
    private void triggerItemQuery(PropertyChangeEvent propertyChangeEvent) {
        Packet p = new Packet(Packet.ItemQuery, (String) propertyChangeEvent.getNewValue());
        System.out.println("ClientSenderTriggerItemQuery");
        addToQueue(p);
    }

    /**
     * Adds an Employee Query to the {@link Queue}.
     *
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that triggered this method to be run.
     */
    private void triggerEmployeeQuery(PropertyChangeEvent propertyChangeEvent) {
        String departmentid = (String) propertyChangeEvent.getNewValue();

        Packet p = new Packet(Packet.EmployeeQuery, departmentid);
        System.out.println("ClientSender: EmployeeQuery");
        addToQueue(p);
    }

    private void addStockItemListener(PropertyChangeEvent propertyChangeEvent) {

        StockItem stockItem = (StockItem) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(stockItem);
        Packet packet = new Packet(Packet.StockOperation, json);
        System.out.println("ClientSender: addStockItemListener StockItemOperation");
        addToQueue(packet);
    }

    private void addEmployeeListener(PropertyChangeEvent propertyChangeEvent) {
        Employee employe = (Employee) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(employe);
        Packet packet = new Packet(Packet.EmployeeOperation, json);
        System.out.println("ClientSender: addEmployeListener EmployeeOperation");
        addToQueue(packet);

    }

    public void addRequestListener(PropertyChangeEvent propertyChangeEvent) {
        ProductRequestList productRequestList = (ProductRequestList) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(productRequestList);
        Packet packet = new Packet(Packet.RequestOperation, json);
        addToQueue(packet);
    }

    /**
     * The run method inherited from {@link Runnable}.
     * This method contains the logic of sending {@link Packet}s stored in the {@link Queue} to the {@link network.Server.ServerReceiver}.
     */
    @Override
    public void run() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            if (queue.isEmpty()) {
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

    /**
     * The method used to add {@link Packet}s the {@link Queue}.
     *
     * @param packet The {@link Packet} to be added.
     */
    public void addToQueue(Packet packet) {
        queue.add(packet);
    }

}

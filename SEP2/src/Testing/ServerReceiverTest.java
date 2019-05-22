package Testing;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.Employee;
import model.EmployeeList;
import network.Packet;
import network.Server.ServerReceiver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceiverTest {

    Socket socket;
    ServerReceiver receiver;
    Thread t1;

    @Before
    public void setUp() throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(1111);
        socket = new Socket("localhost", 1111);
        DataBaseModel dataBaseModel = new DataBaseModel();
        receiver = new ServerReceiver(welcomeSocket.accept(), dataBaseModel);
        t1 = new Thread(receiver);
        t1.start();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void run() throws IOException, InterruptedException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Gson gson = new Gson();
        Employee e = new Employee("kenneth", "jensen", "1", "HQ");
        EmployeeList employeeList = new EmployeeList();
        employeeList.add(e);
        String json = gson.toJson(employeeList);
        Packet packet = new Packet(Packet.EmployeeOperation, json);
        out.writeObject(packet);
        Thread.sleep(500);
    }
}
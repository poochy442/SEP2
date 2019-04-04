package network;

public class Packet {

    private String operation, Json;
    public static final String EmployeeOperation = "Employees", StockOperation = "StockItems";

    public Packet(String operation, String json) {
        this.operation = operation;
        Json = json;
    }

    public String getOperation() {
        return operation;
    }

    public String getJson() {
        return Json;
    }
}

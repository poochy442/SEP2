package model;

import java.sql.Timestamp;

public class Message {
    private String message;
    private Timestamp timestamp;
    private String departmentID;

    public Message(String message, Timestamp timestamp, String departmentID) {
        this.message = message;
        this.timestamp = timestamp;
        this.departmentID = departmentID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", departmentID='" + departmentID + '\'' +
                '}';
    }
}

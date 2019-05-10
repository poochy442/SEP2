package model;

public class Employee {

    private String firstName, lastName, id,departmentID;

    public Employee(String firstName, String lastName, String id, String departmentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.departmentID=departmentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Employee)){
            return false;
        }
        Employee other = (Employee) obj;
        return other.firstName.equals(this.firstName)
                && other.lastName.equals(this.lastName);
    }

    public String getDepartmentid() {
        return departmentID;
    }

    //TODO: add toString
}

package model;

public class Employee {

    private String firstName, lastName;
    // TODO: add ID

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Employee)){
            return false;
        }
        Employee other = (Employee) obj;
        return other.firstName.equals(this.firstName)
                && other.lastName.equals(this.lastName);
    }

    //TODO: add toString
}

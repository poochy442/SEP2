package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private ArrayList<Employee> employeeList;

    public EmployeeList(){
        employeeList = new ArrayList<>();
    }

    public void add(Employee e){
        employeeList.add(e);
    }

    public void add(List<Employee> employees){
        for(Employee e : employees){
            add(e);
        }
    }

    public void remove(int index){
        employeeList.remove(index);
    }

    public void remove(Employee e){
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).equals(e)){
                employeeList.remove(i);
            }
        }
    }

    public Employee get(int index){
        return employeeList.get(index);
    }

    public int size(){
        return employeeList.size();
    }

}

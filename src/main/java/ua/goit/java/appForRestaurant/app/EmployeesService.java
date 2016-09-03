package ua.goit.java.appForRestaurant.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import java.beans.PropertyVetoException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesService {

    public EmployeeDao employeeDao;

    public ObservableList<Employee> getAll(){
        ObservableList<Employee> observableList = FXCollections.observableArrayList();
        observableList.addAll(employeeDao.getAll().stream().collect(Collectors.toList()));
        return observableList;
    }

    public Employee createEmployeeWithId() throws PropertyVetoException {
        Employee e = new Employee();
        List<Employee> employees = employeeDao.getAll();
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return new Integer(o1.getId()).compareTo(o2.getId());
            }
        });
        int id = 0;
        for (Employee employee: employees) {
            if (employee.getId() == id){
                id++;
            }else {
                e.setId(id);
            }
        }
        e.setId(id);
        return e;
    }

    public boolean addEmployee(Employee e){
        e.setName(e.getName().trim());
        e.setSurname(e.getSurname().trim());
        e.setDataBirth(e.getDataBirth().trim());
        e.setPosition(e.getPosition().trim());
        e.setPhone(e.getPhone().trim());
        employeeDao.add(e);
        return true;
    }

    public ObservableList<Employee> getEmploeesByName(String name){
        name = name.trim();
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employees.addAll(employeeDao.getEmployee(name).stream().collect(Collectors.toList()));
        return employees;
    }

   public void deleteEmployee(int id){
       employeeDao.delete(id);
   }

}

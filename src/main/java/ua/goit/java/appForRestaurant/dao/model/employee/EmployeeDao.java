package ua.goit.java.appForRestaurant.dao.model.employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAll();
    public List<Employee> getEmployee(String name);
    public void add(Employee employee);
    public void delete(int id);
}

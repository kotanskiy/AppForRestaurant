package ua.goit.java.appForRestaurant.console.comands.employee;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import java.beans.PropertyVetoException;
import java.util.List;

public class GetAllEmployees implements Command{

    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public String get() {
        return "get-all-employees";
    }

    @Override
    public void run() throws PropertyVetoException {
        List<Employee> employees = employeeDao.getAll();
        for (Employee e: employees) {
            System.out.println(e.toString());
        }
    }
}

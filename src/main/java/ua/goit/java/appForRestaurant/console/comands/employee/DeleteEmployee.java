package ua.goit.java.appForRestaurant.console.comands.employee;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

/**
 * Created by Admin on 03.09.2016.
 */
public class DeleteEmployee implements Command{
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }



    @Override
    public String get() {
        return "delete-employee";
    }

    @Override
    public void run() throws PropertyVetoException {
        Employee employee = new Employee();
        employeeDao.delete(anyEmployee());
    }

    private int anyEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the employee id , which must be removed: ");
        String idString = scanner.nextLine();
        idString = idString.trim();
        return Integer.parseInt(idString);
    }
}

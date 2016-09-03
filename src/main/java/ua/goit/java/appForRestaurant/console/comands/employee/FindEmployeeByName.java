package ua.goit.java.appForRestaurant.console.comands.employee;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Scanner;

public class FindEmployeeByName implements Command{
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public String get() {
        return "find-employee";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        try {
            List<Employee> employees = find(scanner.nextLine());
            for (Employee e: employees) {
                System.out.println(e.toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private List<Employee> find(String name){
        name = name.trim();
        return employeeDao.getEmployee(name);
    }
}

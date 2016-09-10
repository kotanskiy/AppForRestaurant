package ua.goit.java.appForRestaurant.console.comands.employee;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import java.beans.PropertyVetoException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AddEmployee implements Command {

    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public String get() {
        return "add-employee";
    }

    @Override
    public void run() throws PropertyVetoException {
        Employee employee = createEmployeeWithId();
        employee = fillingEmployee(employee);
        employeeDao.add(employee);
    }

    private Employee fillingEmployee(Employee employee){
        Scanner scanner = new Scanner(System.in);
        System.out.print("fill name: ");
        employee.setName(scanner.nextLine().trim());
        System.out.print("fill surname: ");
        employee.setSurname(scanner.nextLine().trim());
        System.out.print("fill data birth: ");
        employee.setDataBirth(scanner.nextLine().trim());
        System.out.print("fill position: ");
        employee.setPosition(scanner.nextLine().trim());
        System.out.print("fill salary: ");
        String salary = scanner.nextLine().trim();
        employee.setSalary(Float.parseFloat(salary));
        System.out.print("fill phone: ");
        employee.setPhone(scanner.nextLine().trim());
        return employee;
    }

    private Employee createEmployeeWithId() throws PropertyVetoException {
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
}

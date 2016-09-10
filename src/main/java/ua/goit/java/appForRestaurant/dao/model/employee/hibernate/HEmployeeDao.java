package ua.goit.java.appForRestaurant.dao.model.employee.hibernate;


import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import java.util.List;


public class HEmployeeDao implements EmployeeDao{


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HEmployeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAll(){
        return null;
    }

    @Override
    public List<Employee> getEmployee(String name) {
        return null;
    }


    @Override
    @Transactional
    public void add(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public void delete(int id) {

    }
}

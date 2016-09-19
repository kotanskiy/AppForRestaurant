package ua.goit.java.appForRestaurant.console.comands.readyDish;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.order.Order;
import ua.goit.java.appForRestaurant.dao.model.readyDish.ReadyDish;
import ua.goit.java.appForRestaurant.dao.model.readyDish.ReadyDishDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

@Transactional
public class AddReadyDish implements Command {

    private ReadyDishDao readyDishDao;

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setReadyDishDao(ReadyDishDao readyDishDao) {
        this.readyDishDao = readyDishDao;
    }

    @Override
    public String get() {
        return "add-ready-dish";
    }

    @Override
    public void run() throws PropertyVetoException {
        readyDishDao.addReadyDish(fillingReadyDish());
    }

    private ReadyDish fillingReadyDish(){
        Scanner scanner = new Scanner(System.in);
        ReadyDish readyDish = new ReadyDish();
        System.out.print("fill id dish: ");
        String idDish = scanner.nextLine().trim();
        Dish dish = sessionFactory.getCurrentSession().get(Dish.class, Integer.parseInt(idDish));
        readyDish.setIdDish(dish);
        System.out.print("fill id employee: ");
        String idEmployee = scanner.nextLine().trim();
        Employee employee = sessionFactory.getCurrentSession().get(Employee.class, Integer.parseInt(idEmployee));
        readyDish.setIdEmployee(employee);
        System.out.print("fill id order: ");
        String idOrder = scanner.nextLine().trim();
        Order order = sessionFactory.getCurrentSession().get(Order.class, Integer.parseInt(idOrder));
        readyDish.setIdOrder(order);
        return readyDish;
    }
}

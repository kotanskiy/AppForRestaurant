package ua.goit.java.appForRestaurant.console.comands.orders;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.order.Order;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.beans.PropertyVetoException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Transactional
public class CreateOrder implements Command{

    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String get() {
        return "create-order";
    }

    @Override
    public void run() throws PropertyVetoException {
        Order order = new Order();
        orderDao.create(fillingOrderWithId(order));
    }

    private Order fillingOrderWithId(Order order){
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter id employee who serve the order: ");
        String idEmployee = scanner.nextLine().trim();
        try {
            employee.setId(Integer.parseInt(idEmployee));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        order.setIdEmployee(employee);
        System.out.print("enter number table: ");
        String numberTable = scanner.nextLine();
        order.setNumberTable(Integer.parseInt(numberTable.trim()));
        return order;
    }

    private Order createOrderWithId() throws PropertyVetoException {
        Order order = new Order();
        try {
            List<Order> allOrders = orderDao.getAllOrders();
            allOrders.sort(new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return new Integer(o1.getId()).compareTo(o2.getId());
                }
            });
            int id = 0;
            for (Order o: allOrders) {
                if (o.getId() == id){
                    id++;
                }else {
                    order.setId(id);
                }
            }
            order.setId(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return order;
    }
}

package ua.goit.java.appForRestaurant.console.comands.orders;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.order.Order;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.beans.PropertyVetoException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
        orderDao.create(fillingOrderWithId(createOrderWithId()));
    }

    private Order fillingOrderWithId(Order order){
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter id employee who serve the order: ");
        String idEmployee = scanner.nextLine();
        order.setIdEmployee(Integer.parseInt(idEmployee.trim()));
        System.out.print("enter number table: ");
        String numberTable = scanner.nextLine();
        order.setNumberTable(Integer.parseInt(numberTable.trim()));
        System.out.print("enter date: ");
        order.setDate(scanner.nextLine().trim());
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

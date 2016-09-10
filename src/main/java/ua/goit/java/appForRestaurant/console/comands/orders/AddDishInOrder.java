package ua.goit.java.appForRestaurant.console.comands.orders;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class AddDishInOrder implements Command{
    Scanner scanner = new Scanner(System.in);

    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String get() {
        return "add-dish-order";
    }

    @Override
    public void run() throws PropertyVetoException {
        orderDao.addDishInOrder(fillingIdOrder(), fillingIdDish());
    }

    private int fillingIdOrder(){
        System.out.print("Enter the id order: ");
        String id = scanner.nextLine().trim();
        return Integer.parseInt(id);
    }

    private int fillingIdDish(){
        System.out.print("Enter the id dish: ");
        String id = scanner.nextLine().trim();
        return Integer.parseInt(id);
    }
}

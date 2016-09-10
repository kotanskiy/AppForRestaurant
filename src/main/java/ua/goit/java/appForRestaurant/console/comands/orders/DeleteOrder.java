package ua.goit.java.appForRestaurant.console.comands.orders;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class DeleteOrder implements Command{

    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String get() {
        return "delete-order";
    }

    @Override
    public void run() throws PropertyVetoException {
        orderDao.delete(anyOrder());
    }

    private int anyOrder(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the order id , which must be removed: ");
        String idString = scanner.nextLine();
        idString = idString.trim();
        return Integer.parseInt(idString);
    }
}

package ua.goit.java.appForRestaurant.console.comands.orders;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class SetOrderState implements Command{

    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String get() {
        return "set-state-order";
    }

    @Override
    public void run() throws PropertyVetoException {
        switch (fillingState()){
            case "open":
                orderDao.setState(fillingIdOrder(), true);
                break;
            case "close":
                orderDao.setState(fillingIdOrder(), false);
                break;
            default:
                System.out.println("It is necessary to enter : 'open' or 'close'.");
                break;
        }
    }

    private String fillingState(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the order status(open/close): ");
        return scanner.nextLine().trim();
    }

    private int fillingIdOrder(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the id order: ");
        String id = scanner.nextLine().trim();
        return Integer.parseInt(id);
    }
}

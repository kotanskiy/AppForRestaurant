package ua.goit.java.appForRestaurant.console.comands.orders;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.order.Order;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.beans.PropertyVetoException;
import java.util.List;

public class GetCloseOrders implements Command{

    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String get() {
        return "get-close-orders";
    }

    @Override
    public void run() throws PropertyVetoException {
        List<Order> orders = orderDao.getOpenOrCloseOrders(false);
        for (Order o: orders) {
            System.out.println(o.toString());
        }
    }
}

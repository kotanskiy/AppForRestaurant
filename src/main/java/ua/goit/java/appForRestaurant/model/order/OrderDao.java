package ua.goit.java.appForRestaurant.model.order;

import java.util.List;

public interface OrderDao {
    public void create(Order order);
    public void delete(int idOrder);
    public void setState(int idOrder,boolean state);
    public void addDishInOrder(int idOrder,int idDish);
    public void deleteDishInOrder(int idOrder, int idDish);
    public List<Order> getOpenOrCloseOrders(boolean state);
}

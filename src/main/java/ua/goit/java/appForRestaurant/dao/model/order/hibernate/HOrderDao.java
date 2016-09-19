package ua.goit.java.appForRestaurant.dao.model.order.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.order.Order;
import ua.goit.java.appForRestaurant.dao.model.order.OrderDao;

import java.util.ArrayList;
import java.util.List;

public class HOrderDao implements OrderDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void create(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Transactional
    @Override
    public void delete(int idOrder) {
        Query query =  sessionFactory.getCurrentSession().createQuery("delete customer_order where id = :id");
        query.setParameter("id", idOrder);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void setState(int idOrder, boolean state) {
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        order.setState(state);
        sessionFactory.getCurrentSession().update(order);
    }

    @Transactional
    @Override
    public void addDishInOrder(int idOrder, int idDish) {
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        Dish dish = sessionFactory.getCurrentSession().get(Dish.class, idDish);
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        order.setDishList(dishes);
        sessionFactory.getCurrentSession().update(order);
    }

    @Transactional
    @Override
    public void deleteDishInOrder(int idOrder, int idDish) {
        Order order = sessionFactory.getCurrentSession().get(Order.class, idOrder);
        List<Dish> dishes = order.getDishList();
        dishes.remove(idOrder - 1);
        order.setDishList(dishes);
        sessionFactory.getCurrentSession().update(order);
    }

    @Transactional
    @Override
    public List<Order> getOpenOrCloseOrders(boolean state) {
        List<Order> orders = sessionFactory.getCurrentSession().createQuery("from Order where state =:state").setParameter("state", state).list();
        return orders;
    }

    @Transactional
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = sessionFactory.getCurrentSession().createQuery("from Order").list();
        return orders;
    }
}

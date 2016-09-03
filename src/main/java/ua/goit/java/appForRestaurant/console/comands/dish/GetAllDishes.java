package ua.goit.java.appForRestaurant.console.comands.dish;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.dish.DishDao;

import java.beans.PropertyVetoException;
import java.util.List;

public class GetAllDishes implements Command{
    private DishDao dishDao;

    public DishDao getDishDao() {
        return dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public String get() {
        return "get-all-dishes";
    }

    @Override
    public void run() throws PropertyVetoException {
        List<Dish> dishes = dishDao.getAll();
        for (Dish dish: dishes) {
            System.out.println(dish.toString());
        }
    }
}

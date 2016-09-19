package ua.goit.java.appForRestaurant.console.comands.readyDish;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.readyDish.ReadyDishDao;

import java.beans.PropertyVetoException;

public class GetReadyDish implements Command{

    private ReadyDishDao readyDishDao;

    public void setReadyDishDao(ReadyDishDao readyDishDao) {
        this.readyDishDao = readyDishDao;
    }

    @Override
    public String get() {
        return "get-ready-dish";
    }

    @Override
    public void run() throws PropertyVetoException {
        readyDishDao.getReadyDishes().forEach(System.out::println);
    }
}

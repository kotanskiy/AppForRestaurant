package ua.goit.java.appForRestaurant.console.comands.dish;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.dish.DishDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class DeleteDish implements Command{

    private DishDao dishDao;

    public DishDao getDishDao() {
        return dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public String get() {
        return "delete-dish";
    }

    @Override
    public void run() throws PropertyVetoException {
        dishDao.delete(anyDish());
    }

    private int anyDish(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the dish id , which must be removed: ");
        String idString = scanner.nextLine();
        idString = idString.trim();
        return Integer.parseInt(idString);
    }
}

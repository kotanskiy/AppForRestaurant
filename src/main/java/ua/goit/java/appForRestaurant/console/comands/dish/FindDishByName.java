package ua.goit.java.appForRestaurant.console.comands.dish;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.dish.DishDao;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Scanner;

public class FindDishByName implements Command{

    private DishDao dishDao;

    public DishDao getDishDao() {
        return dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public String get() {
        return "find-dish";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        try {
            List<Dish> dishes = find(scanner.nextLine());
            for (Dish dish: dishes) {
                System.out.println(dish.toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private List<Dish> find(String name){
        name = name.trim();
        return dishDao.getDish(name);
    }
}

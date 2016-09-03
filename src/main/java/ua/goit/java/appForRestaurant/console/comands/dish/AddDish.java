package ua.goit.java.appForRestaurant.console.comands.dish;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.dish.DishDao;

import java.beans.PropertyVetoException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AddDish implements Command{

    private DishDao dishDao;

    public DishDao getDishDao() {
        return dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public String get() {
        return "add-dish";
    }

    @Override
    public void run() throws PropertyVetoException {
        Dish dish = fillingDishWithId(createDishWithId());
        try {
            dishDao.add(dish);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private Dish createDishWithId(){
        Dish dish = new Dish();
        List<Dish> dishes = dishDao.getAll();
        dishes.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return new Integer(o1.getId()).compareTo(o2.getId());
            }
        });
        int id = 0;
        for (Dish d: dishes) {
            if (d.getId() == id){
                id++;
            }else {
                dish.setId(id);
            }
        }
        dish.setId(id);
        return dish;
    }

    private Dish fillingDishWithId(Dish dish){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        dish.setName(scanner.nextLine().trim());
        System.out.print("Enter category: ");
        dish.setCategory(scanner.nextLine().trim());
        System.out.print("Enter cost of: ");
        String costOf = scanner.nextLine().trim();
        dish.setCostOf(Float.parseFloat(costOf));
        System.out.print("Enter weight: ");
        String weight = scanner.nextLine().trim();
        dish.setWeight(Float.parseFloat(weight));
        return dish;
    }
}

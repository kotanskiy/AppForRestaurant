package ua.goit.java.appForRestaurant.model.dish;

import java.util.List;

public interface DishDao {
    public void add(Dish dish);
    public void delete(int id);
    public List<Dish> getDish(String name);
    public List<Dish> getAll();
}

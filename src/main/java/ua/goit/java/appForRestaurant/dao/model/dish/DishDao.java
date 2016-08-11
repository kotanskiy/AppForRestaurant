package ua.goit.java.appForRestaurant.dao.model.dish;

import java.util.List;

public interface DishDao {
    public void add(Dish dish);
    public void addIngredientInDish(int idDish, int idIngredient, int count);
    public void deleteIngredientInDish(int idDish, int idIngredient);
    public void delete(int id);
    public List<Dish> getDish(String name);
    public List<Dish> getAll();
}

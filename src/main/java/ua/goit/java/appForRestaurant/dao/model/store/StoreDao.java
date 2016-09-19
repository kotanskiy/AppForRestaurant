package ua.goit.java.appForRestaurant.dao.model.store;

import java.util.List;

public interface StoreDao {
    public void addIngredientInList(String name);
    public void addIngredientInStore(int idIngredient, int count);
    public void deleteIngredientInStore(int idIngredient);
    public void setIngredientsInStore(int idIngredient, int count);
    public Ingredient getIngredient(String name);
    public List<Ingredient> getAllIngredients();
    public List<Store> ingredientsLessThan(int count);
}

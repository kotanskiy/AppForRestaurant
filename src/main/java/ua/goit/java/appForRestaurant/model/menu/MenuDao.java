package ua.goit.java.appForRestaurant.model.menu;

import java.util.List;

public interface MenuDao {
    public Menu create(int id, String name);
    public void delete(int id);
    public void addDishInMenu(int idMenu, int idDish);
    public void deleteDishInMenu(int idMenu, int idDish);
    public List<Menu> getMenu(String name);
    public List<Menu> getAll();
}

package ua.goit.java.appForRestaurant.model.ready_dish;

import java.util.List;

public interface ReadyDishDao {
    public void addReadyDish(ReadyDish readyDish);
    public List<ReadyDish> getReadyDishes();
}

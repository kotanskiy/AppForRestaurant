package ua.goit.java.appForRestaurant.dao.model.readyDish;

import java.util.List;

public interface ReadyDishDao {
    public void addReadyDish(ReadyDish readyDish);
    public List<ReadyDish> getReadyDishes();
}

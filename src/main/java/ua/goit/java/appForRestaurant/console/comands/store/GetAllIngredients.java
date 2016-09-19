package ua.goit.java.appForRestaurant.console.comands.store;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.store.Ingredient;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import java.beans.PropertyVetoException;
import java.util.List;

public class GetAllIngredients implements Command{

    private StoreDao storeDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public String get() {
        return "get-all-ingredients";
    }

    @Override
    public void run() throws PropertyVetoException {
        List<Ingredient> ingredients = storeDao.getAllIngredients();
        for (Ingredient i: ingredients) {
            System.out.println(i.toString());
        }
    }
}

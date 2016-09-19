package ua.goit.java.appForRestaurant.console.comands.store;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.store.Store;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Scanner;

public class IngredientsLessThan implements Command{

    private StoreDao storeDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public String get() {
        return "get-ingredients-less";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lest than: ");
        String count = scanner.nextLine().trim();
        List<Store> ingredients = storeDao.ingredientsLessThan(Integer.parseInt(count));
        if (ingredients.isEmpty()){
            System.out.println("all ingredients more " + count);
        }else {
            for (Store s: ingredients) {
                System.out.println(s.toString());
            }
        }
    }
}

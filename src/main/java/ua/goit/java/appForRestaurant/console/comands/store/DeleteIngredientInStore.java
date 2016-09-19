package ua.goit.java.appForRestaurant.console.comands.store;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class DeleteIngredientInStore implements Command{

    private StoreDao storeDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public String get() {
        return "delete-ingredient-store";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id ingredient: ");
        String idIngredient = scanner.nextLine().trim();
        storeDao.deleteIngredientInStore(Integer.parseInt(idIngredient));
    }
}

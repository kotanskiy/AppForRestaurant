package ua.goit.java.appForRestaurant.console.comands.store;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class SetIngredientInStore implements Command{

    private StoreDao storeDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public String get() {
        return "set-ingredient-store";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id ingredient: ");
        String idIngredient = scanner.nextLine().trim();
        System.out.print("Enter count: ");
        String count = scanner.nextLine().trim();
        storeDao.setIngredientsInStore(Integer.parseInt(idIngredient), Integer.parseInt(count));
    }
}

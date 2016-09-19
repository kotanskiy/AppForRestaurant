package ua.goit.java.appForRestaurant.console.comands.store;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.store.Ingredient;
import ua.goit.java.appForRestaurant.dao.model.store.Store;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

@Transactional
public class FindIngredientInStore implements Command{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private StoreDao storeDao;

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public String get() {
        return "find-ingredient-store";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        Ingredient ingredient = storeDao.getIngredient(name);
        System.out.println(sessionFactory.getCurrentSession().get(Store.class, ingredient.getId()).toString());
    }
}

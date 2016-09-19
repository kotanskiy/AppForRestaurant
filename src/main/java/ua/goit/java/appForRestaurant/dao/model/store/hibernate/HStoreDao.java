package ua.goit.java.appForRestaurant.dao.model.store.hibernate;


import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.store.Ingredient;
import ua.goit.java.appForRestaurant.dao.model.store.Store;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import java.util.List;
import java.util.stream.Collectors;

public class HStoreDao implements StoreDao{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addIngredientInList(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        sessionFactory.getCurrentSession().save(ingredient);
    }

    @Override
    @Transactional
    public void addIngredientInStore(int idIngredient, int count) {
        Store store = new Store();
        store.setIdIngredient(sessionFactory.getCurrentSession().get(Ingredient.class, idIngredient));
        store.setCount(count);
        sessionFactory.getCurrentSession().save(store);
    }

    @Override
    @Transactional
    public void deleteIngredientInStore(int idIngredient) {
        //remove all ingredients in store
        Store store = new Store();
        Ingredient ingredient = sessionFactory.getCurrentSession().get(Ingredient.class, idIngredient);
        store.setIdIngredient(ingredient);
        sessionFactory.getCurrentSession().delete(store);
    }

    @Override
    @Transactional
    public void setIngredientsInStore(int idIngredient, int count) {
        Store store = new Store();
        store.setIdIngredient(sessionFactory.getCurrentSession().get(Ingredient.class, idIngredient));
        store.setCount(count);
        sessionFactory.getCurrentSession().update(store);
    }

    @Override
    @Transactional
    public Ingredient getIngredient(String name) {
        return (Ingredient) sessionFactory.getCurrentSession().createQuery("from Ingredient where name =:name").setParameter("name", name).uniqueResult();
    }

    @Override
    @Transactional
    public List<Ingredient> getAllIngredients() {
        return sessionFactory.getCurrentSession().createQuery("from Ingredient").list();
    }

    @Override
    @Transactional
    public List<Store> ingredientsLessThan(int count) {
        List<Store> allIngredients = sessionFactory.getCurrentSession().createQuery("from Store").list();
        return allIngredients.stream().filter(ingredient -> ingredient.getCount() < count).collect(Collectors.toList());
    }
}

package ua.goit.java.appForRestaurant.dao.model.store;

public class Store {
    private int idIngredient;
    private int count;

    @Override
    public String toString() {
        return "Store{" +
                "idIngredient=" + idIngredient +
                ", count=" + count +
                '}';
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

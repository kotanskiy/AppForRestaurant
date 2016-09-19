package ua.goit.java.appForRestaurant.dao.model.store;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "store")
public class Store implements Serializable{

    @Id
    @OneToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient idIngredient;

    @Column(name = "count")
    private int count;

    @Override
    public String toString() {
        return "Store{" +
                "idIngredient=" + idIngredient +
                ", count=" + count +
                '}';
    }


    public Ingredient getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Ingredient idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

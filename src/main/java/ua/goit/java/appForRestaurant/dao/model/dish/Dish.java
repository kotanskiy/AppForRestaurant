package ua.goit.java.appForRestaurant.dao.model.dish;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "cost_of")
    private float costOf;

    @Column(name = "weight")
    private float weight;

    @ManyToMany
    @JoinTable(
            name = "ingredients_in_dishes",
            inverseJoinColumns = @JoinColumn(name = "id_dish"),
            joinColumns = @JoinColumn(name = "id_ingredient")
    )
    private List<Dish> ingredientsInDish;

    public List<Dish> getIngredientsInDish() {
        return ingredientsInDish;
    }

    public void setIngredientsInDish(List<Dish> ingredientsInDish) {
        this.ingredientsInDish = ingredientsInDish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCostOf() {
        return costOf;
    }

    public void setCostOf(float costOf) {
        this.costOf = costOf;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", costOf=" + costOf +
                ", weight=" + weight +
                '}';
    }
}

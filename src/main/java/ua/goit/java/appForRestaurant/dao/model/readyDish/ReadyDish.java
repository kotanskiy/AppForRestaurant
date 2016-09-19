package ua.goit.java.appForRestaurant.dao.model.readyDish;


import org.hibernate.annotations.GenericGenerator;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.order.Order;

import javax.persistence.*;

@Entity
@Table(name = "ready_dishes")
public class ReadyDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_dish")
    private Dish idDish;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee idEmployee;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order idOrder;

//    @Column(name = "ready_date")
//    private String readyDate;

    @Override
    public String toString() {
        return "ReadyDish{" +
                "id=" + id +
                ", idDish=" + idDish +
                ", idEmployee=" + idEmployee +
                ", idOrder=" + idOrder +
              //  ", readyDate='" + readyDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   // public String getReadyDate() {
     //   return readyDate;
    //}

//    public void setReadyDate(String readyDate) {
//        this.readyDate = readyDate;
//    }
    public Dish getIdDish() {
        return idDish;
    }

    public void setIdDish(Dish idDish) {
        this.idDish = idDish;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }
}

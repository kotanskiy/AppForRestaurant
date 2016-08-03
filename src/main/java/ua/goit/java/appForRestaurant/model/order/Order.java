package ua.goit.java.appForRestaurant.model.order;

public class Order {
    private int id;
    private int idEmployee;
    private int numberTable;
    private String date;
    private boolean state;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idEmployee=" + idEmployee +
                ", numberTable=" + numberTable +
                ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}

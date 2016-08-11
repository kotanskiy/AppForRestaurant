package ua.goit.java.appForRestaurant.dao.model.readyDish;

public class ReadyDish {
    private int id;
    private int idDish;
    private int idEmployee;
    private int idOrder;
    private String readyDate;

    @Override
    public String toString() {
        return "ReadyDish{" +
                "id=" + id +
                ", idDish=" + idDish +
                ", idEmployee=" + idEmployee +
                ", idOrder=" + idOrder +
                ", readyDate='" + readyDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReadyDate() {
        return readyDate;
    }

    public void setReadyDate(String readyDate) {
        this.readyDate = readyDate;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }
}

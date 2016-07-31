package ua.goit.java.appForRestaurant.model.employee;

import java.beans.PropertyVetoException;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private String dataBirth;
    private String position;
    private float salary;
    private String phone;

    @Override
    public String toString() {
        return "ua.goit.java.appForRestaurant.model.employee.Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", data_birth='" + dataBirth + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws PropertyVetoException {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDataBirth() {
        return dataBirth;
    }

    public void setDataBirth(String data_birth) {
        this.dataBirth = data_birth;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

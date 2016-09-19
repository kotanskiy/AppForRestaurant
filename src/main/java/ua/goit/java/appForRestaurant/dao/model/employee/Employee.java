package ua.goit.java.appForRestaurant.dao.model.employee;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.beans.PropertyVetoException;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_birth")
    private String dataBirth;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private float salary;

    @Column(name = "phone")
    private String phone;

    public Employee(){

    }

    public Employee(int id, String phone, float salary, String name, String surname, String dataBirth, String position) {
        this.id = id;
        this.phone = phone;
        this.salary = salary;
        this.name = name;
        this.surname = surname;
        this.dataBirth = dataBirth;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
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

package ua.goit.java.appForRestaurant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.java.appForRestaurant.config.AppConfig;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException, PropertyVetoException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig config = context.getBean(AppConfig.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println(config.jdbcEmployeeDao().getAll().toString());
    }
}

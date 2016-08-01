package ua.goit.java.appForRestaurant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.java.appForRestaurant.Config.AppConfig;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException, PropertyVetoException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig config = context.getBean(AppConfig.class);
        Scanner scanner = new Scanner(System.in);
        System.out.print("input idMenu: ");
        int idMenu = scanner.nextInt();
        System.out.print("input idDish: ");
        int idDish = scanner.nextInt();
        config.jdbcMenuDao().addDishInMenu(idMenu, idDish);
    }
}

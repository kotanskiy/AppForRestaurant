package ua.goit.java.appForRestaurant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.java.appForRestaurant.config.AppConfig;

import java.beans.PropertyVetoException;

public class RunnerForConsole {
    public static void main(String[] args) throws PropertyVetoException{
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig config = context.getBean(AppConfig.class);
        System.out.println("__________________App for restaurant(version for console)___________________");
        //config.jdbcEmployeeDao().add(new Employee(25, "+34343553", 203032.0F, "lalka", "dsdsdsdsd", "199109-23", "waiter"));
        config.parser().run();
    }
}

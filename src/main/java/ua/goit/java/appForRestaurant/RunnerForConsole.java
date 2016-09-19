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
        config.parser().run();
    }
}

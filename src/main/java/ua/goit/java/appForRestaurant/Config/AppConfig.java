package ua.goit.java.appForRestaurant.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ua.goit.java.appForRestaurant.Test;
import ua.goit.java.appForRestaurant.model.dish.jdbc.JdbcDishDao;
import ua.goit.java.appForRestaurant.model.employee.jdbc.JdbcEmployeeDao;
import ua.goit.java.appForRestaurant.model.menu.jdbc.JdbcMenuDao;

import java.beans.PropertyVetoException;

@Configuration
public class AppConfig {

    @Bean
    public Test test() {
        return new Test();
    }

    @Bean
    public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbs:postgresql://localhost:5432/restaurant");
        dataSource.setUser("user");
        dataSource.setPassword("katavasia");

        dataSource.setMinPoolSize(1);
        dataSource.setMaxPoolSize(10);
        dataSource.setAcquireIncrement(1);
        return dataSource;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("jdbc.properties"));
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public JdbcEmployeeDao jdbcEmployeeDao() throws PropertyVetoException {
        JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.dataSource = comboPooledDataSource();
        return  jdbcEmployeeDao;
    }

    @Bean
    public JdbcDishDao jdbcDishDao() throws PropertyVetoException {
        JdbcDishDao jdbcDishDao = new JdbcDishDao();
        jdbcDishDao.dataSource = comboPooledDataSource();
        return jdbcDishDao;
    }

    @Bean
    public JdbcMenuDao jdbcMenuDao() throws PropertyVetoException {
        JdbcMenuDao jdbcMenuDao = new JdbcMenuDao();
        jdbcMenuDao.dataSource = comboPooledDataSource();
        return jdbcMenuDao;
    }

}

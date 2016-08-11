package ua.goit.java.appForRestaurant.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.goit.java.appForRestaurant.dao.model.dish.jdbc.JdbcDishDao;
import ua.goit.java.appForRestaurant.dao.model.employee.jdbc.JdbcEmployeeDao;
import ua.goit.java.appForRestaurant.dao.model.menu.jdbc.JdbcMenuDao;
import ua.goit.java.appForRestaurant.dao.model.order.jdbc.JdbcOrderDao;
import ua.goit.java.appForRestaurant.dao.model.readyDish.jdbc.JdbcReadyDishDao;
import ua.goit.java.appForRestaurant.dao.model.store.jdbc.JdbcStoreDao;

import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:jdbc.properties")
public  class AppConfig {

    @Value("${jdbc.driver.class}")
    String jdbcDriverClass;

    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.user}")
    String jdbcUser;

    @Value("${jdbc.password}")
    String jdbcPassword;

    @Value("${jdbc.max.connections}")
    String jdbcMaxConnections;

    @Value("${jdbc.min.connections}")
    String jdbcMinConnections;

    @Value("${jdbc.acquire.increment}")
    String jdbcAcquireIncrement;


    @Bean
    public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setPassword(jdbcPassword);

        dataSource.setMinPoolSize(Integer.parseInt(jdbcMinConnections));
        dataSource.setMaxPoolSize(Integer.parseInt(jdbcMaxConnections));
        dataSource.setAcquireIncrement(Integer.parseInt(jdbcAcquireIncrement));
        return dataSource;
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

    @Bean
    public JdbcOrderDao jdbcOrderDao() throws PropertyVetoException {
        JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();
        jdbcOrderDao.dataSource = comboPooledDataSource();
        return jdbcOrderDao;
    }

    @Bean
    public JdbcReadyDishDao jdbcReadyDishDao() throws PropertyVetoException {
        JdbcReadyDishDao jdbcReadyDishDao = new JdbcReadyDishDao();
        jdbcReadyDishDao.dataSource = comboPooledDataSource();
        return jdbcReadyDishDao;
    }

    @Bean
    public JdbcStoreDao jdbcStoreDao() throws PropertyVetoException {
        JdbcStoreDao jdbcStoreDao = new JdbcStoreDao();
        jdbcStoreDao.dataSource = comboPooledDataSource();
        return jdbcStoreDao;
    }

}

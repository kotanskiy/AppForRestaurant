import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.beans.PropertyVetoException;

@Configuration
public class AppConfig {

    @Bean
    public Test test() throws PropertyVetoException {
        Test test = new Test();
        test.setDataSource(comboPooledDataSource());
        return test;
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
}

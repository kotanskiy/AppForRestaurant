import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws SQLException, PropertyVetoException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig config = context.getBean(AppConfig.class);
        config.test().loadDriver();


        List<Employee> employees = config.test().testLoadDataBase();
        for (Employee e: employees) {
            System.out.println(e.getName() + " " + e.getSurname());
        }

    }

    private void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private List<Employee> testLoadDataBase() throws SQLException {
        List<Employee> resultQuery = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                resultQuery.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultQuery;
    }
}

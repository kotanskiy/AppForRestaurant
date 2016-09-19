package ua.goit.java.appForRestaurant.dao.model.employee.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;
import ua.goit.java.appForRestaurant.dao.model.employee.EmployeeDao;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDao implements EmployeeDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDao.class);

    public DataSource dataSource;


    private Employee create(ResultSet resultSet) throws SQLException, PropertyVetoException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setDataBirth(resultSet.getString("date_birth"));
        employee.setPosition(resultSet.getString("position"));
        employee.setSalary(resultSet.getFloat("salary"));
        employee.setPhone(resultSet.getString("phone"));
        return employee;
    }

    @Transactional
    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()){
                employees.add(create(resultSet));
            }
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        } catch (PropertyVetoException e) {
            LOGGER.error("invalid query");
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Transactional
    @Override
    public List<Employee> getEmployee(String name) {
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            boolean flag = true;

            while (resultSet.next()){
                employees.add(create(resultSet));
                flag = false;
            }

            if (flag){
                throw new RuntimeException("this name is not present in the database");
            }else {
                LOGGER.info("get complete");
            }

        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        } catch (PropertyVetoException e) {
            LOGGER.error("invalid query");
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Transactional
    @Override
    public void add(Employee employee) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getName());
            statement.setDate(4, java.sql.Date.valueOf(employee.getDataBirth()));
            statement.setString(5, employee.getPosition());
            statement.setFloat(6, employee.getSalary());
            statement.setString(7, employee.getPhone());
            statement.execute();
            LOGGER.info("add successful");
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException("invalid input data");
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info("Delete complete");
        }catch (SQLException e){
            LOGGER.error("invalid connection");
            throw new RuntimeException(e);
        }
    }
}

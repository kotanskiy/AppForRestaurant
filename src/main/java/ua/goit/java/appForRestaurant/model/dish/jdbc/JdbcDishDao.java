package ua.goit.java.appForRestaurant.model.dish.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.model.dish.Dish;
import ua.goit.java.appForRestaurant.model.dish.DishDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishDao implements DishDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcDishDao.class);

    public DataSource dataSource;

    @Transactional
    @Override
    public void add(Dish dish) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dish VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, dish.getId());
            statement.setString(2, dish.getName());
            statement.setString(3, dish.getCategory());
            statement.setFloat(4, dish.getCostOf());
            statement.setFloat(5, dish.getWeight());
            statement.execute();
            LOGGER.info("add complete");
        }catch (SQLException e){
            LOGGER.error("add error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM dish WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            LOGGER.info("delete complete");
        }catch (SQLException e){
            LOGGER.error("delete error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public List<Dish> getDish(String name) {
        List<Dish> dishes = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dish WHERE name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                dishes.add(create(resultSet));
            }
            LOGGER.info("getDish successful");
        }catch (SQLException e){
            LOGGER.error("getDish error");
            throw new RuntimeException(e);
        }
        return dishes;
    }

    @Transactional
    @Override
    public List<Dish> getAll() {
        List<Dish> dishes = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dish");
            while (resultSet.next()){
                dishes.add(create(resultSet));
            }
            LOGGER.info("getAll successful");
        }catch (SQLException e){
            LOGGER.error("getDish error");
            throw new RuntimeException(e);
        }
        return dishes;
    }

    private Dish create(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id"));
        dish.setName(resultSet.getString("name"));
        dish.setCategory(resultSet.getString("category"));
        dish.setCostOf(resultSet.getFloat("cost_of"));
        dish.setWeight(resultSet.getFloat("weight"));
        return dish;
    }
}

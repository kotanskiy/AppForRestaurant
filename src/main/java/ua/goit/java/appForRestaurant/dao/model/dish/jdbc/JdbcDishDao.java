package ua.goit.java.appForRestaurant.dao.model.dish.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.dish.Dish;
import ua.goit.java.appForRestaurant.dao.model.dish.DishDao;

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
    public void addIngredientInDish(int idDish, int idIngredient, int count){
        if (count <= 0){
            throw new RuntimeException("count < 1");
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementSetIngredientInDish = connection.prepareStatement("INSERT INTO ingredients_in_dishes VALUES (?, ?, ?)");
            PreparedStatement statementGetIngredientInDish = connection.prepareStatement("SELECT * FROM ingredients_in_dishes WHERE id_dish = ? AND id_ingredient = ?");
            PreparedStatement statementIncrementCountIngredientInDish = connection.prepareStatement("UPDATE ingredients_in_dishes SET count = count + ? WHERE id_dish = ? AND id_ingredient = ?")) {
            statementGetIngredientInDish.setInt(1, idDish);
            statementGetIngredientInDish.setInt(2, idIngredient);

            boolean incrementCountOrInsert = true;

            ResultSet resultSet = statementGetIngredientInDish.executeQuery();
            boolean equals = resultSet.next();

            if (equals){
                if (resultSet.getInt("id_dish")== idDish && resultSet.getInt("id_ingredient") == idIngredient){
                    incrementCountOrInsert = false;
                }
            }

            if (incrementCountOrInsert){
                statementSetIngredientInDish.setInt(1, idDish);
                statementSetIngredientInDish.setInt(2, idIngredient);
                statementSetIngredientInDish.setInt(3, count);
                statementSetIngredientInDish.execute();
                LOGGER.info("Insert new Ingredients in dish");
            }else {
                statementIncrementCountIngredientInDish.setInt(1, count);
                statementIncrementCountIngredientInDish.setInt(2, idDish);
                statementIncrementCountIngredientInDish.setInt(3, idIngredient);
                statementIncrementCountIngredientInDish.execute();
                LOGGER.info("count++");
            }


            LOGGER.info("addIngredientInDish successful");
        }catch (SQLException e){
            LOGGER.error("addIngredientInDish error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteIngredientInDish(int idDish, int idIngredient){
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM ingredients_in_dishes WHERE id_dish = ? AND id_ingredient = ?")) {
            statement.setInt(1, idDish);
            statement.setInt(2, idIngredient);
            statement.execute();
            LOGGER.info("deleteIngredientInDis successful");
        }catch (SQLException e){
            LOGGER.error("deleteIngredientInDish error");
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
            boolean flag = true;

            while (resultSet.next()){
                dishes.add(create(resultSet));
                flag = false;
            }

            if (flag){
                throw new RuntimeException("this name is not present in the database");
            }else {
                LOGGER.info("get complete");
            }
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

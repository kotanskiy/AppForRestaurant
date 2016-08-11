package ua.goit.java.appForRestaurant.dao.model.store.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.store.Ingredient;
import ua.goit.java.appForRestaurant.dao.model.store.StoreDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStoreDao implements StoreDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStoreDao.class);

    public DataSource dataSource;

    @Transactional
    @Override
    public void addIngredientInList(int id, String name) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ingredients VALUES (?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.execute();
            LOGGER.info("addIngredientInList successful");
        }catch (SQLException e){
            LOGGER.error("addIngredientInList error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void addIngredientInStore(int idIngredient, int count) {
        if (count <= 0){
            throw new RuntimeException("count < 1");
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementSetIngredientInStore = connection.prepareStatement("INSERT INTO store VALUES (?, ?)");
            PreparedStatement statementGetIngredientInStore = connection.prepareStatement("SELECT * FROM store WHERE id_ingredient = ?");
            PreparedStatement statementIncrementCountIngredientInStore = connection.prepareStatement("UPDATE store SET count = count + ? WHERE id_ingredient = ?")) {
            statementGetIngredientInStore.setInt(1, idIngredient);

            boolean incrementCountOrInsert = true;

            ResultSet resultSet = statementGetIngredientInStore.executeQuery();
            boolean equals = resultSet.next();

            if (equals){
                if (resultSet.getInt("id_ingredient") == idIngredient){
                    incrementCountOrInsert = false;
                }
            }

            if (incrementCountOrInsert){
                statementSetIngredientInStore.setInt(1, idIngredient);
                statementSetIngredientInStore.setInt(2, count);
                statementSetIngredientInStore.execute();
                LOGGER.info("Insert new Ingredients in store");
            }else {
                statementIncrementCountIngredientInStore.setInt(1, count);
                statementIncrementCountIngredientInStore.setInt(2, idIngredient);
                statementIncrementCountIngredientInStore.execute();
                LOGGER.info("count++");
            }


            LOGGER.info("addIngredientInStore successful");
        }catch (SQLException e){
            LOGGER.error("addIngredientInStore error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteIngredientInStore(int idIngredient) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementGetIngredientInStore = connection.prepareStatement("SELECT * FROM store WHERE id_ingredient = ?");
            PreparedStatement statementDeleteIngredientInStore = connection.prepareStatement("DELETE FROM store WHERE id_ingredient = ?");
            PreparedStatement statementDecrementCountIngredientInStore = connection.prepareStatement("UPDATE store SET count = count - 1 WHERE id_ingredient = ?")) {
            statementGetIngredientInStore.setInt(1, idIngredient);

            ResultSet resultSet = statementGetIngredientInStore.executeQuery();
            resultSet.next();

            if (resultSet.getInt("count") > 1){
                statementDecrementCountIngredientInStore.setInt(1, idIngredient);
                statementDecrementCountIngredientInStore.execute();
                LOGGER.info("count--");
            }else {
                statementDeleteIngredientInStore.setInt(1, idIngredient);
                statementDeleteIngredientInStore.execute();
                LOGGER.info("Delete");
            }
        }catch (SQLException e){
            LOGGER.error("deleteIngredientInStore error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void setIngredientsInStore(int idIngredient, int count) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE store SET count = ? WHERE id_ingredient = ?")) {
            statement.setInt(1, count);
            statement.setInt(2, idIngredient);
            statement.execute();
            LOGGER.info("setIngredientsInStore successful");
        }catch (SQLException e){
            LOGGER.error("setIngredientsInStore error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Ingredient getIngredient(String name) {
        Ingredient ingredient;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients WHERE name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ingredient = createIngredient(resultSet);
            LOGGER.info("getIngredient successful");
        }catch (SQLException e){
            LOGGER.error("getIngredient error");
            throw new RuntimeException(e);
        }
        return ingredient;
    }

    @Transactional
    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ingredients");
            while (resultSet.next()){
                ingredients.add(createIngredient(resultSet));
            }
            LOGGER.info("getIngredients successful");
        }catch (SQLException e){
            LOGGER.error("getIngredients error");
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    @Transactional
    @Override
    public List<Ingredient> ingredientsLessThan(int count) {
        List<Ingredient> ingredients = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statementGetId = connection.prepareStatement("SELECT id_ingredient FROM store WHERE count < ?");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients WHERE id = ?")) {
            statementGetId.setInt(1, count);
            ResultSet resultId = statementGetId.executeQuery();
            ResultSet resultSet;
            while (resultId.next()){
                statement.setInt(1, resultId.getInt("id_ingredient"));
                resultSet = statement.executeQuery();
                resultSet.next();
                ingredients.add(createIngredient(resultSet));
            }
            LOGGER.info("ingredientsLessThan successful");
        }catch (SQLException e){
            LOGGER.error("ingredientsLessThan error");
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    private Ingredient createIngredient(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));
        return ingredient;
    }
}

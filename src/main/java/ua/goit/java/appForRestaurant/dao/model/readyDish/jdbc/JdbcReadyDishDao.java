package ua.goit.java.appForRestaurant.dao.model.readyDish.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.appForRestaurant.dao.model.readyDish.ReadyDish;
import ua.goit.java.appForRestaurant.dao.model.readyDish.ReadyDishDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
public class JdbcReadyDishDao implements ReadyDishDao{


    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcReadyDishDao.class);

    public DataSource dataSource;


    private ReadyDish create(ResultSet resultSet) throws SQLException {
        ReadyDish readyDish = new ReadyDish();
        readyDish.setId(resultSet.getInt("id"));
        readyDish.setIdDish(resultSet.getInt("id_dish"));
        readyDish.setIdEmployee(resultSet.getInt("id_employee"));
        readyDish.setIdOrder(resultSet.getInt("id_order"));
        readyDish.setReadyDate(resultSet.getString("ready_date"));
        return readyDish;
    }

    @Transactional
    @Override
    public void addReadyDish(ReadyDish readyDish) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ready_dishes VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, readyDish.getId());
            statement.setInt(2, readyDish.getIdDish());
            statement.setInt(3, readyDish.getIdEmployee());
            statement.setInt(4, readyDish.getIdOrder());
            statement.setDate(5, java.sql.Date.valueOf(readyDish.getReadyDate()));
            statement.execute();
        }catch (SQLException e){
            LOGGER.error("addReadyDishes error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public List<ReadyDish> getReadyDishes() {
        List<ReadyDish> readyDishes = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ready_dishes");
            boolean flag = true;

            while (resultSet.next()){
                readyDishes.add(create(resultSet));
                flag = false;
            }

            if (flag){
                throw new RuntimeException("Ready dishes is not present in the database");
            }else {
                LOGGER.info("getReadyDishes complete");
            }
        }catch (SQLException e){
            LOGGER.error("getReadyDishes error");
            throw new RuntimeException(e);
        }
        return readyDishes;
    }
}
*/
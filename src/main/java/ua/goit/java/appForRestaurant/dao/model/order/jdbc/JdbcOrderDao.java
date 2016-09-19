package ua.goit.java.appForRestaurant.dao.model.order.jdbc;

/*
public class JdbcOrderDao implements OrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcOrderDao.class);

    public DataSource dataSource;

    @Transactional
    @Override
    public void create(Order order) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer_order VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getIdEmployee());
            statement.setInt(3, order.getNumberTable());
            statement.setDate(4, java.sql.Date.valueOf(order.getDate()));
            statement.setBoolean(5, true);
            statement.execute();
            LOGGER.info("create successful");
        }catch (SQLException e){
            LOGGER.error("create error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void delete(int idOrder) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer_order WHERE id = ?");
            PreparedStatement statementGetState = connection.prepareStatement("SELECT state FROM customer_order WHERE id = ?")) {

            statementGetState.setInt(1, idOrder);
            ResultSet state = statementGetState.executeQuery();
            state.next();

            if (state.getBoolean("state")){
                statement.setInt(1, idOrder);
                if (statement.execute()){
                    LOGGER.info("delete successful");
                }else {
                    LOGGER.error("this order with id is not exist");
                    throw new RuntimeException("this order with id is not exist");
                }


            }else {
                LOGGER.error("this order is close");
                throw new RuntimeException("this order is close");
            }
        }catch (SQLException e){
            LOGGER.error("delete error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void setState(int idOrder,boolean state) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE customer_order SET state = ? WHERE id = ?")) {
            statement.setBoolean(1, state);
            statement.setInt(2, idOrder);
            statement.execute();
            LOGGER.info("setState successful");
        }catch (SQLException e){
            LOGGER.error("setState error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void addDishInOrder(int idOrder, int idDish) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementSetDish = connection.prepareStatement("INSERT INTO dish_list VALUES (?, ?, ?)");
            PreparedStatement statementGetDish = connection.prepareStatement("SELECT * FROM dish_list WHERE id_customer_order = ?");
            PreparedStatement statementIncrementCountDish = connection.prepareStatement("UPDATE dish_list SET count = count + 1 WHERE id_customer_order = ? AND id_dish = ?");
            PreparedStatement statementGetState = connection.prepareStatement("SELECT state FROM customer_order WHERE id = ?")) {

            statementGetState.setInt(1, idOrder);
            ResultSet state = statementGetState.executeQuery();
            state.next();


            if (state.getBoolean("state")){
                statementGetDish.setInt(1, idOrder);

                boolean incrementCountOrInsert = true;

                ResultSet resultSet = statementGetDish.executeQuery();
                while (resultSet.next()){
                    if (resultSet.getInt("id_dish") == idDish){
                        incrementCountOrInsert = false;
                        break;
                    }
                }

                if (incrementCountOrInsert){
                    statementSetDish.setInt(1, idOrder);
                    statementSetDish.setInt(2, idDish);
                    statementSetDish.setInt(3, 1);
                    statementSetDish.execute();
                    LOGGER.info("Insert new dish in order");
                }else {
                    statementIncrementCountDish.setInt(1, idOrder);
                    statementIncrementCountDish.setInt(2, idDish);
                    statementIncrementCountDish.execute();
                    LOGGER.info("count++");
                }


                LOGGER.info("addDishInOrder successful");
            }else {
                LOGGER.error("this order is close");
                throw new RuntimeException("this order is close");
            }
        }catch (SQLException e){
            LOGGER.error("addDish error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteDishInOrder(int idOrder, int idDish) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statementGetDish = connection.prepareStatement("SELECT * FROM dish_list WHERE id_customer_order = ? AND id_dish = ?");
            PreparedStatement statementDeleteDish = connection.prepareStatement("DELETE FROM dish_list WHERE id_customer_order = ? AND id_dish = ?");
            PreparedStatement statementDecrementCountDish = connection.prepareStatement("UPDATE dish_list SET count = count - 1 WHERE id_customer_order = ? AND id_dish = ?");
            PreparedStatement statementGetState = connection.prepareStatement("SELECT state FROM customer_order WHERE id = ?")) {

            statementGetState.setInt(1, idOrder);
            ResultSet state = statementGetState.executeQuery();
            state.next();

            if (state.getBoolean("state")){
                statementGetDish.setInt(1, idOrder);
                statementGetDish.setInt(2, idDish);

                ResultSet resultSet = statementGetDish.executeQuery();
                resultSet.next();

                if (resultSet.getInt("count") > 1){
                    statementDecrementCountDish.setInt(1, idOrder);
                    statementDecrementCountDish.setInt(2,idDish);
                    statementDecrementCountDish.execute();
                    LOGGER.info("count--");
                }else {
                    statementDeleteDish.setInt(1, idOrder);
                    statementDeleteDish.setInt(2, idDish);
                    statementDeleteDish.execute();
                    LOGGER.info("Delete");
                }
            }else {
                LOGGER.error("this order is close");
                throw new RuntimeException("this order is close");
            }

        }catch (SQLException e){
            LOGGER.error("deleteDishOrder error");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public List<Order> getOpenOrCloseOrders(boolean state) {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer_order WHERE state = ?")) {
            statement.setBoolean(1, state);
            ResultSet resultSet = statement.executeQuery();

            boolean flag = true;

            while (resultSet.next()){
                orders.add(createOrder(resultSet));
                flag = false;
            }

            if (flag){
                throw new RuntimeException("this orders is not present in the database");
            }else {
                LOGGER.info("getOpenOrders complete");
            }

        }catch (SQLException e){
            LOGGER.error("getOpenOrders error");
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Transactional
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer_order");

            while (resultSet.next()){
                orders.add(createOrder(resultSet));
            }
        }catch (SQLException e){
            LOGGER.error("getAllOrders error");
            throw new RuntimeException(e);
        }
        return orders;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setIdEmployee(resultSet.getInt("id_employee"));
        order.setNumberTable(resultSet.getInt("number_table"));
        order.setDate(resultSet.getString("order_date"));
        order.setState(resultSet.getBoolean("state"));
        return order;
    }
}
*/

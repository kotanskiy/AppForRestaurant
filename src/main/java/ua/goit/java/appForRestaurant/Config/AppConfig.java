package ua.goit.java.appForRestaurant.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.goit.java.appForRestaurant.app.EmployeesService;
import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.console.Parser;
import ua.goit.java.appForRestaurant.console.comands.dish.AddDish;
import ua.goit.java.appForRestaurant.console.comands.dish.DeleteDish;
import ua.goit.java.appForRestaurant.console.comands.dish.FindDishByName;
import ua.goit.java.appForRestaurant.console.comands.dish.GetAllDishes;
import ua.goit.java.appForRestaurant.console.comands.employee.AddEmployee;
import ua.goit.java.appForRestaurant.console.comands.employee.DeleteEmployee;
import ua.goit.java.appForRestaurant.console.comands.employee.FindEmployeeByName;
import ua.goit.java.appForRestaurant.console.comands.employee.GetAllEmployees;
import ua.goit.java.appForRestaurant.console.comands.menu.*;
import ua.goit.java.appForRestaurant.console.comands.orders.*;
import ua.goit.java.appForRestaurant.console.comands.readyDish.AddReadyDish;
import ua.goit.java.appForRestaurant.console.comands.readyDish.GetReadyDish;
import ua.goit.java.appForRestaurant.console.comands.store.*;
import ua.goit.java.appForRestaurant.dao.model.dish.jdbc.JdbcDishDao;
import ua.goit.java.appForRestaurant.dao.model.employee.hibernate.HEmployeeDao;
import ua.goit.java.appForRestaurant.dao.model.employee.jdbc.JdbcEmployeeDao;
import ua.goit.java.appForRestaurant.dao.model.menu.jdbc.JdbcMenuDao;
import ua.goit.java.appForRestaurant.dao.model.order.hibernate.HOrderDao;
import ua.goit.java.appForRestaurant.dao.model.readyDish.hibernate.HReadiDishDao;
import ua.goit.java.appForRestaurant.dao.model.store.hibernate.HStoreDao;
import ua.goit.java.appForRestaurant.gui.MainController;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.*;

@Configuration
@EnableJpaRepositories("com.panterafox.common")
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public  class AppConfig {

    //db properties
    private static final String JDBC_DRIVER_CLASS = "jdbc.driver.class";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.user";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String JDBC_MIN_CONNECTIONS = "jdbc.min.connections";
    private static final String JDBC_MAX_CONNECTIONS = "jdbc.max.connections";
    private static final String JDBC_ACQUIRE_INCREMENT = "jdbc.acquire.increment";

    //hibernate properties
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PCKG_TO_SCAN = "entitymanager.packages.to.scan";

    @Autowired
    private Environment env;

    @Bean
    public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty(JDBC_DRIVER_CLASS));
        dataSource.setJdbcUrl(env.getProperty(JDBC_URL));
        dataSource.setUser(env.getProperty(JDBC_USER));
        dataSource.setPassword(env.getProperty(JDBC_PASSWORD));

        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty(JDBC_MIN_CONNECTIONS)));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty(JDBC_MAX_CONNECTIONS)));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty(JDBC_ACQUIRE_INCREMENT)));
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        try {
            localSessionFactoryBean.setDataSource(comboPooledDataSource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        localSessionFactoryBean.setPackagesToScan(env.getProperty(PCKG_TO_SCAN));
        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        try {
            localSessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localSessionFactoryBean.getObject();
    }

    @Autowired
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory());
        return hibernateTransactionManager;
    }

    @Bean
    public Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        properties.setProperty(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
        properties.setProperty(PCKG_TO_SCAN, env.getProperty(PCKG_TO_SCAN));
        return properties;
    }

    @Bean
    public HEmployeeDao hEmployeeDao(){
        return new HEmployeeDao(sessionFactory());
    }

    @Bean
    public HReadiDishDao hReadiDishDao(){
        HReadiDishDao hReadiDishDao = new HReadiDishDao();
        hReadiDishDao.setSessionFactory(sessionFactory());
        return hReadiDishDao;
    }

    @Bean
    public HOrderDao hOrderDao(){
        HOrderDao hOrderDao = new HOrderDao();
        hOrderDao.setSessionFactory(sessionFactory());
        return hOrderDao;
    }

    @Bean
    public HStoreDao hStoreDao(){
        HStoreDao hStoreDao = new HStoreDao();
        hStoreDao.setSessionFactory(sessionFactory());
        return hStoreDao;
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
/*
    @Bean
    public JdbcOrderDao jdbcOrderDao() throws PropertyVetoException {
        JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();
        jdbcOrderDao.dataSource = comboPooledDataSource();
        return jdbcOrderDao;
    }
*/
    /*
    @Bean
    public JdbcReadyDishDao jdbcReadyDishDao() throws PropertyVetoException {
        JdbcReadyDishDao jdbcReadyDishDao = new JdbcReadyDishDao();
        jdbcReadyDishDao.dataSource = comboPooledDataSource();
        return jdbcReadyDishDao;
    }
*/
    /*
    @Bean
    public JdbcStoreDao jdbcStoreDao() throws PropertyVetoException {
        JdbcStoreDao jdbcStoreDao = new JdbcStoreDao();
        jdbcStoreDao.dataSource = comboPooledDataSource();
        return jdbcStoreDao;
    }
*/
    @Bean
    public EmployeesService employeesGui() throws PropertyVetoException {
        EmployeesService employeesService = new EmployeesService();
        employeesService.employeeDao = jdbcEmployeeDao();
        return employeesService;
    }

    @Bean
    public MainController mainController(){
        return new MainController();
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public AddEmployee addEmployee() throws PropertyVetoException{
        AddEmployee addEmployee = new AddEmployee();
        addEmployee.setEmployeeDao(hEmployeeDao());
        return addEmployee;
    }

    @Bean
    public DeleteEmployee deleteEmployee() throws PropertyVetoException {
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        deleteEmployee.setEmployeeDao(hEmployeeDao());
        return deleteEmployee;
    }

    @Bean
    public GetAllEmployees getAllEmployees() throws PropertyVetoException {
        GetAllEmployees getAllEmployees = new GetAllEmployees();
        getAllEmployees.setEmployeeDao(hEmployeeDao());
        return getAllEmployees;
    }

    @Bean
    public FindEmployeeByName findEmployeeByName() throws PropertyVetoException {
        FindEmployeeByName findEmployeeByName = new FindEmployeeByName();
        findEmployeeByName.setEmployeeDao(hEmployeeDao());
        return findEmployeeByName;
    }

    @Bean
    public AddDish addDish() throws PropertyVetoException {
        AddDish addDish = new AddDish();
        addDish.setDishDao(jdbcDishDao());
        return addDish;
    }

    @Bean
    public DeleteDish deleteDish() throws PropertyVetoException {
        DeleteDish deleteDish = new DeleteDish();
        deleteDish.setDishDao(jdbcDishDao());
        return deleteDish;
    }

    @Bean
    public GetAllDishes getAllDishes() throws PropertyVetoException {
        GetAllDishes getAllDishes = new GetAllDishes();
        getAllDishes.setDishDao(jdbcDishDao());
        return getAllDishes;
    }

    @Bean
    public FindDishByName findDishByName() throws PropertyVetoException {
        FindDishByName findDishByName = new FindDishByName();
        findDishByName.setDishDao(jdbcDishDao());
        return findDishByName;
    }

    @Bean
    public CreateMenu createMenu() throws PropertyVetoException {
        CreateMenu createMenu = new CreateMenu();
        createMenu.setMenuDao(jdbcMenuDao());
        return createMenu;
    }

    @Bean
    public DeleteMenu deleteMenu() throws PropertyVetoException {
        DeleteMenu deleteMenu = new DeleteMenu();
        deleteMenu.setMenuDao(jdbcMenuDao());
        return deleteMenu;
    }

    @Bean
    public GetAllMenu getAllMenu() throws PropertyVetoException {
        GetAllMenu getAllMenu = new GetAllMenu();
        getAllMenu.setMenuDao(jdbcMenuDao());
        return getAllMenu;
    }

    @Bean
    public FindMenuByName findMenuByName() throws PropertyVetoException {
        FindMenuByName findMenuByName = new FindMenuByName();
        findMenuByName.setMenuDao(jdbcMenuDao());
        return findMenuByName;
    }

    @Bean
    public AddDishMenu addDishMenu() throws PropertyVetoException {
        AddDishMenu addDishMenu = new AddDishMenu();
        addDishMenu.setMenuDao(jdbcMenuDao());
        return addDishMenu;
    }

    @Bean
    public DeleteDishMenu deleteDishMenu() throws PropertyVetoException {
        DeleteDishMenu deleteDishMenu = new DeleteDishMenu();
        deleteDishMenu.setMenuDao(jdbcMenuDao());
        return deleteDishMenu;
    }

    @Bean
    public CreateOrder createOrder() throws PropertyVetoException {
        CreateOrder createOrder = new CreateOrder();
        createOrder.setOrderDao(hOrderDao());
        return createOrder;
    }

    @Bean
    public GetAllOrders getAllOrders() throws PropertyVetoException {
        GetAllOrders getAllOrders = new GetAllOrders();
        getAllOrders.setOrderDao(hOrderDao());
        return getAllOrders;
    }

    @Bean
    public GetOpenOrders getOpenOrders() throws PropertyVetoException {
        GetOpenOrders getOpenOrders = new GetOpenOrders();
        getOpenOrders.setOrderDao(hOrderDao());
        return getOpenOrders;
    }

    @Bean
    public GetCloseOrders getCloseOrders() throws PropertyVetoException {
        GetCloseOrders getCloseOrders = new GetCloseOrders();
        getCloseOrders.setOrderDao(hOrderDao());
        return getCloseOrders;
    }

    @Bean
    public SetOrderState setOrderState() throws PropertyVetoException {
        SetOrderState setOrderState = new SetOrderState();
        setOrderState.setOrderDao(hOrderDao());
        return setOrderState;
    }

    @Bean
    public DeleteOrder deleteOrder() throws PropertyVetoException {
        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.setOrderDao(hOrderDao());
        return deleteOrder;
    }

    @Bean
    public AddDishInOrder addDishInOrder() throws PropertyVetoException {
        AddDishInOrder addDishInOrder = new AddDishInOrder();
        addDishInOrder.setOrderDao(hOrderDao());
        return addDishInOrder;
    }

    @Bean
    public DeleteDishInOrder deleteDishInOrder() throws PropertyVetoException {
        DeleteDishInOrder deleteDishInOrder = new DeleteDishInOrder();
        deleteDishInOrder.setOrderDao(hOrderDao());
        return deleteDishInOrder;
    }

    @Bean
    public AddReadyDish addReadyDish(){
        AddReadyDish addReadyDish = new AddReadyDish();
        addReadyDish.setReadyDishDao(hReadiDishDao());
        addReadyDish.setSessionFactory(sessionFactory());
        return addReadyDish;
    }

    @Bean
    public GetReadyDish getReadyDish(){
        GetReadyDish getReadyDish = new GetReadyDish();
        getReadyDish.setReadyDishDao(hReadiDishDao());
        return getReadyDish;
    }

    @Bean
    public AddIngredientInList addIngredientInList(){
        AddIngredientInList addIngredientInList = new AddIngredientInList();
        addIngredientInList.setStoreDao(hStoreDao());
        return addIngredientInList;
    }

    @Bean
    public AddIngredientInStore addIngredientInStore(){
        AddIngredientInStore addIngredientInStore = new AddIngredientInStore();
        addIngredientInStore.setStoreDao(hStoreDao());
        return addIngredientInStore;
    }

    @Bean
    public DeleteIngredientInStore deleteIngredientInStore(){
        DeleteIngredientInStore deleteIngredientInStore = new DeleteIngredientInStore();
        deleteIngredientInStore.setStoreDao(hStoreDao());
        return deleteIngredientInStore;
    }

    @Bean
    public SetIngredientInStore setIngredientInStore(){
        SetIngredientInStore setIngredientInStore = new SetIngredientInStore();
        setIngredientInStore.setStoreDao(hStoreDao());
        return setIngredientInStore;
    }

    @Bean
    public FindIngredientInStore findIngredientInStore(){
        FindIngredientInStore findIngredientInStore = new FindIngredientInStore();
        findIngredientInStore.setStoreDao(hStoreDao());
        findIngredientInStore.setSessionFactory(sessionFactory());
        return findIngredientInStore;
    }

    @Bean
    public GetAllIngredients getAllIngredients(){
        GetAllIngredients getAllIngredients = new GetAllIngredients();
        getAllIngredients.setStoreDao(hStoreDao());
        return getAllIngredients;
    }

    @Bean
    public IngredientsLessThan ingredientsLessThan(){
        IngredientsLessThan ingredientsLessThan = new IngredientsLessThan();
        ingredientsLessThan.setStoreDao(hStoreDao());
        return ingredientsLessThan;
    }

    @Bean
    public List<Command> listCommands() throws PropertyVetoException{
        List<Command> commands = new ArrayList<>();
        commands.add(addEmployee());
        commands.add(deleteEmployee());
        commands.add(getAllEmployees());
        commands.add(findEmployeeByName());
        commands.add(addDish());
        commands.add(deleteDish());
        commands.add(getAllDishes());
        commands.add(findDishByName());
        commands.add(createMenu());
        commands.add(deleteMenu());
        commands.add(getAllMenu());
        commands.add(findMenuByName());
        commands.add(addDishMenu());
        commands.add(deleteDishMenu());
        commands.add(createOrder());
        commands.add(getAllOrders());
        commands.add(getOpenOrders());
        commands.add(getCloseOrders());
        commands.add(setOrderState());
        commands.add(deleteOrder());
        commands.add(addDishInOrder());
        commands.add(deleteDishInOrder());
        commands.add(addReadyDish());
        commands.add(getReadyDish());
        commands.add(addIngredientInList());
        commands.add(addIngredientInStore());
        commands.add(deleteIngredientInStore());
        commands.add(setIngredientInStore());
        commands.add(findIngredientInStore());
        commands.add(getAllIngredients());
        commands.add(ingredientsLessThan());
        return commands;
    }

    @Bean
    public Map<String, Command> initializationCommands(List<Command> listCommands){
        Map<String, Command> commands = new HashMap<>();
        for (Command command: listCommands) {
            commands.put(command.get(), command);
        }
        return commands;
    }

    @Bean
    public Parser parser() throws PropertyVetoException{
        Parser parser = new Parser();
        parser.setCommands(initializationCommands(listCommands()));
        return parser;
    }
}

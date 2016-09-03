package ua.goit.java.appForRestaurant.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
import ua.goit.java.appForRestaurant.console.comands.orders.CreateOrder;
import ua.goit.java.appForRestaurant.console.comands.orders.GetAllOrders;
import ua.goit.java.appForRestaurant.dao.model.dish.jdbc.JdbcDishDao;
import ua.goit.java.appForRestaurant.dao.model.employee.jdbc.JdbcEmployeeDao;
import ua.goit.java.appForRestaurant.dao.model.menu.jdbc.JdbcMenuDao;
import ua.goit.java.appForRestaurant.dao.model.order.jdbc.JdbcOrderDao;
import ua.goit.java.appForRestaurant.dao.model.readyDish.jdbc.JdbcReadyDishDao;
import ua.goit.java.appForRestaurant.dao.model.store.jdbc.JdbcStoreDao;
import ua.goit.java.appForRestaurant.gui.MainController;

import java.beans.PropertyVetoException;
import java.util.*;

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

    @Bean(name = "jdbcEmployeeDao")
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
    public AddEmployee addEmployee() throws PropertyVetoException {
        AddEmployee addEmployee = new AddEmployee();
        addEmployee.setEmployeeDao(jdbcEmployeeDao());
        return addEmployee;
    }

    @Bean
    public DeleteEmployee deleteEmployee() throws PropertyVetoException {
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        deleteEmployee.setEmployeeDao(jdbcEmployeeDao());
        return deleteEmployee;
    }

    @Bean
    public GetAllEmployees getAllEmployees() throws PropertyVetoException {
        GetAllEmployees getAllEmployees = new GetAllEmployees();
        getAllEmployees.setEmployeeDao(jdbcEmployeeDao());
        return getAllEmployees;
    }

    @Bean
    public FindEmployeeByName findEmployeeByName() throws PropertyVetoException {
        FindEmployeeByName findEmployeeByName = new FindEmployeeByName();
        findEmployeeByName.setEmployeeDao(jdbcEmployeeDao());
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
        createOrder.setOrderDao(jdbcOrderDao());
        return createOrder;
    }

    @Bean
    public GetAllOrders getAllOrders() throws PropertyVetoException {
        GetAllOrders getAllOrders = new GetAllOrders();
        getAllOrders.setOrderDao(jdbcOrderDao());
        return getAllOrders;
    }


    @Bean
    public List<Command> listCommands() throws PropertyVetoException {
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
    public Parser parser() throws PropertyVetoException {
        Parser parser = new Parser();
        parser.setCommands(initializationCommands(listCommands()));
        return parser;
    }
}

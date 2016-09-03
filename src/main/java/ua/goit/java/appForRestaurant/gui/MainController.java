package ua.goit.java.appForRestaurant.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.java.appForRestaurant.app.EmployeesService;
import ua.goit.java.appForRestaurant.config.AppConfig;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;

import java.io.IOException;
import java.io.InputStream;

public class MainController extends AbstractController implements Controller{
    private ApplicationContext context;
    private EditEmployeeController editEmployeeController;
    private Employee e;

    @FXML
    public TextField findEmployee;

    @FXML
    public TableColumn<Employee, String> name;

    @FXML
    public TableColumn<Employee, String> surname;

    @FXML
    public TableColumn<Employee, String> date;

    @FXML
    public TableColumn<Employee, String> position;

    @FXML
    public TableColumn<Employee, Float> salary;

    @FXML
    public TableColumn<Employee, String> phone;

    @FXML
    public TableView<Employee> tableEmployees;

    private EmployeesService employeesService;
    private ObservableList<Employee> employees;



    @FXML
    public void addEmployee(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/addEmployee.fxml"));
        stage.setTitle("Добавить сотрудника");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    @FXML
    public void setTableEmployyes(ObservableList<Employee> e){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableEmployees.setItems(e);
    }


    public void update(){
        try {
            employees = employeesService.getAll();
        }catch (Exception e){
            showErrorDialog("Ошибка", "Ошибка подключения к базе данных: " + e.getMessage());
        }

        setTableEmployyes(employees);
    }

    public void updateEmployees(ActionEvent actionEvent) {
        update();
    }

    @FXML
    private void initialize(){
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        employeesService = context.getBean(EmployeesService.class);
        update();
    }

    @FXML
    public void editEmployee(ActionEvent actionEvent) throws IOException {
        e = tableEmployees.getSelectionModel().getSelectedItem();
        if (e == null){
            showInfoDialog("Инфо", "Выберете сотрудника");
        }else {
            final String fxml = "/editEmployee.fxml";
            FXMLLoader loader = new FXMLLoader();
            InputStream in = getClass().getResourceAsStream(fxml);
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource(fxml));
            Stage stage = new Stage();
            Parent root = loader.load(in);
            stage.setTitle("Изменить сотрудника");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());

            editEmployeeController = loader.getController();
            editEmployeeController.idEmployee.setText(Integer.toString(e.getId()));
            editEmployeeController.addname.setText(e.getName());
            editEmployeeController.addsurname.setText(e.getSurname());
            editEmployeeController.adddate.setText(e.getDataBirth());
            editEmployeeController.addposition.setText(e.getPosition());
            editEmployeeController.addsalary.setText(Float.toString(e.getSalary()));
            editEmployeeController.addphone.setText(e.getPhone());
            stage.show();
        }
    }

    @FXML
    public void getEmployeeByName(ActionEvent actionEvent) {
        try {
            employees = employeesService.getEmploeesByName(findEmployee.getText());
            setTableEmployyes(employees);
        }catch (Exception e){
            showErrorDialog("Ошибка", "Такого сотрудника нету в базе: " + e.getMessage());
        }
    }

    @FXML
    public void showInfoDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    @FXML
    public void showErrorDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public ApplicationContext getContext() {
        return context;
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ObservableList<Employee> employees) {
        this.employees = employees;
    }
    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }

    public EditEmployeeController getEditEmployeeController() {
        return editEmployeeController;
    }

    public void setEditEmployeeController(EditEmployeeController editEmployeeController) {
        this.editEmployeeController = editEmployeeController;
    }

    @FXML
    public void deleteEmployee(ActionEvent actionEvent) {
        e = tableEmployees.getSelectionModel().getSelectedItem();
        if (e == null){
            showInfoDialog("Инфо", "Выберете сотрудника, которого надо удалить");
        }else {
            try {
                context.getBean(EmployeesService.class).deleteEmployee(e.getId());
                update();
            }catch (Exception e){
                showErrorDialog("Ошибка", "У этого сотрудника есть задание: " + e.getMessage());
            }
        }
    }
}

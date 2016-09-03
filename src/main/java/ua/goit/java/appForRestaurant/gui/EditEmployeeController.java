package ua.goit.java.appForRestaurant.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import ua.goit.java.appForRestaurant.app.EmployeesService;
import ua.goit.java.appForRestaurant.dao.model.employee.Employee;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

public class EditEmployeeController {


    private MainController controller;
    private ApplicationContext context;
    private EmployeesService gui;

    @FXML
    public Button btnEdit;

    @FXML
    public TextField idEmployee;

    @FXML
    public TextField addname;

    @FXML
    public TextField addsurname;

    @FXML
    public TextField adddate;

    @FXML
    public TextField addposition;

    @FXML
    public TextField addsalary;

    @FXML
    public TextField addphone;


    @FXML
    public void initialize() throws IOException {
        final String fxml = "/app.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream in = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        loader.load(in);
        controller = loader.getController();
        context = controller.getContext();
        gui = context.getBean(EmployeesService.class);
    }

    @FXML
    public void addEmployee(ActionEvent actionEvent) throws PropertyVetoException {
        try {
            Employee e = gui.createEmployeeWithId();
            e.setName(addname.getText());
            e.setSurname(addsurname.getText());
            e.setDataBirth(adddate.getText());
            e.setPosition(addposition.getText());
            e.setSalary(Float.parseFloat(addsalary.getText()));
            e.setPhone(addphone.getText());
            gui.addEmployee(e);
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            controller.update();
        }catch (Exception e){
            controller.showErrorDialog("Ошибка", "Ошибка ввода: " + e.getMessage());
        }
    }

    @FXML
    public void editEmployee(ActionEvent actionEvent) throws PropertyVetoException {
        try {
            gui.deleteEmployee(Integer.parseInt(idEmployee.getText()));
            addEmployee(actionEvent);
            controller.update();
        }catch (Exception e){
            controller.showErrorDialog("Ошибка", "Ошибка ввода: " + e.toString());
        }

    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public EmployeesService getGui() {
        return gui;
    }

    public void setGui(EmployeesService gui) {
        this.gui = gui;
    }
}

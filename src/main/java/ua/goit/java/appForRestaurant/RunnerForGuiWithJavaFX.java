package ua.goit.java.appForRestaurant;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.goit.java.appForRestaurant.config.SpringFXMLLoader;
import ua.goit.java.appForRestaurant.gui.MainController;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class RunnerForGuiWithJavaFX extends Application{
    public static void main(String[] args) throws SQLException, PropertyVetoException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = (MainController) SpringFXMLLoader.load("/app.fxml");
        primaryStage.setTitle("App for restaurant");
        primaryStage.setScene(new Scene((Parent) controller.getView()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

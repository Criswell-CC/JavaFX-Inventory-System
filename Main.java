package InventorySystem;

import InventorySystem.models.Inventory;
import InventorySystem.models.Product;
import InventorySystem.models.inHouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * Main application class responsible for initializing data and launching JavaFX application.
 *
 */

public class Main extends Application {

    /**
     * Override of standard JavaFX method to load initial data.
     */
    @Override
    public void init() {

    }

    /**
     * Standard JavaFX method to load FXML for initial form and show window
     *
     * @param primaryStage primary application window automatically passed.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./views/mainForm.fxml"));
        primaryStage.setTitle("Inventory System");
        primaryStage.setScene(new Scene(root, 1050, 500));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Main method launches application
     * @param args passed command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

package InventorySystem.controllers;

import InventorySystem.models.Part;
import InventorySystem.models.Product;
import InventorySystem.models.Inventory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import InventorySystem.utilities.Validator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controller class to handle UI and data interaction logic for the main form of the application.
 *
 */

public class mainFormController implements Initializable {

    @FXML
    private TextField partsSearchBox;
    @FXML
    private TextField productsSearchBox;

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    /**
     * Handles add part button click by opening add part form in new window.
     * @param event button click event.
     * @throws IOException
     */
    public void onAddPart(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root;

        FXMLLoader loader= new FXMLLoader(getClass().getResource("../views/addModifyPartForm.fxml"));
        loader.setController(new InventorySystem.controllers.addPartFormController());

        root = loader.load();

        Scene scene = new Scene(root, 480, 380);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * Handles clicking modify part button by checking if a part is selected and opening new form window if so.
     * @param event button click event
     * @throws IOException
     */
    public void onModifyPart(ActionEvent event) throws IOException {

        if (Inventory.getAllParts().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a part first");
            alert.showAndWait();
        }

        else if (partsTable.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to modify");
            alert.showAndWait();
        }

        else {

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent root;

            FXMLLoader loader= new FXMLLoader(getClass().getResource("../views/addModifyPartForm.fxml"));

            modifyPartFormController controller = new InventorySystem.controllers.modifyPartFormController();

            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

            controller.setPart(selectedPart);

            loader.setController(controller);

            root = loader.load();

            Scene scene = new Scene(root, 480, 380);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        }
    }

    /**
     * Handles clicking of delete part button by checking if a part is selected, prompting confirmation, and calling
     * deletePart method from Inventory class.
     * @param event button click event.
     */
    public void onDeletePart(ActionEvent event) {

        if (partsTable.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        }

        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?", ButtonType.YES, ButtonType.CANCEL);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());;
            }
        }

    }

    /**
     * Handles add product click by opening add product form in new window.
     * @param event button click event.
     * @throws IOException
     */
    public void onAddProduct(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addModifyProductForm.fxml"));
        loader.setController(new InventorySystem.controllers.addProductFormController());

        root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * Handles modify product button click by checking if any products are available and a product is selected and opens
     * modify product form if so.
     * @param event
     * @throws IOException
     */
    public void onModifyProduct(ActionEvent event) throws IOException {

        if (Inventory.getAllProducts().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a product first");
            alert.showAndWait();
        }

        else if (productsTable.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a product to modify");
            alert.showAndWait();
        }

        else {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addModifyProductForm.fxml"));

            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

            modifyProductFormController controller = new InventorySystem.controllers.modifyProductFormController();

            controller.setProduct(selectedProduct);

            loader.setController(controller);

            root = loader.load();

            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }

    /**
     * Handles delete product click by checking if a product is selected and has associated parts and deletes if passes
     * these checks.
     * @param event
     */
    public void onDeleteProduct(ActionEvent event) {

        if (productsTable.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
        }

        else if (!productsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Product with associated parts cannot be deleted.");
            alert.showAndWait();
        }

        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?", ButtonType.YES, ButtonType.CANCEL);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Inventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    /**
     * Handles search bar event by validating input, checking if search string is part ID or part name, and showing error
     * if no results found or updating TableView component with returned results.
     * @param event enter key hit event
     */
    public void onSearchPart(KeyEvent event) {

        String searchText = partsSearchBox.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {

            partsTable.setItems(Inventory.getAllParts());
            return;

        }

        ObservableList<Part> searchResults = FXCollections.observableArrayList();

        if (Validator.validateNonNegativeInt(searchText)) {

            for (Part part : Inventory.getAllParts()) {
                if (part.getId() == Integer.parseInt(searchText)) {
                    searchResults.add(part);
                }
            }
        }

        else {

            for (Part part : Inventory.getAllParts()) {
                if (part.getName().toLowerCase().contains(searchText)) {
                    searchResults.add(part);
                }
            }

        }

        if (searchResults.isEmpty()) {

            partsTable.setItems(null);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No results found.");
            alert.showAndWait();

        }

        else {

            partsTable.setItems(searchResults);

            partsTable.requestFocus();
            partsTable.getSelectionModel().select(0);
            partsTable.getFocusModel().focus(0);

        }
    }

    /**
     * Handles search bar event by validating input, checking if search string is product ID or product name, and showing
     * error if no results found or updating TableView component with returned results.
     * @param event enter key hit event
     */
    public void onSearchProduct(KeyEvent event) {

        String searchText = productsSearchBox.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {

            productsTable.setItems(Inventory.getAllProducts());
            return;

        }

        ObservableList<Product> searchResults = FXCollections.observableArrayList();

        if (Validator.validateNonNegativeInt(searchText)) {
            for (Product product : Inventory.getAllProducts()) {
                if (product.getId() == Integer.parseInt(searchText)) {
                    searchResults.add(product);
                }
            }
        }

        else {
            for (Product product : Inventory.getAllProducts()) {
                if (product.getName().toLowerCase().contains(searchText)) {
                    searchResults.add(product);
                }
            }
        }

        if (searchResults.isEmpty()) {

            productsTable.setItems(null);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No results found.");
            alert.showAndWait();

        }

        else {
            productsTable.setItems(searchResults);

            partsTable.requestFocus();
            partsTable.getSelectionModel().select(0);
            partsTable.getFocusModel().focus(0);

        }

    }

    /**
     * Handles exit button click event by closing the program.
     * @param event button click event.
     */
    @FXML
    public void onExit(ActionEvent event) {

        Platform.exit();

    }

    /**
     * Override of standard JavaFX method to set enter key event handling on search boxes, associate object types with
     * table columns, and load table data.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partsSearchBox.setFocusTraversable(false);
        productsSearchBox.setFocusTraversable(false);

        //https://stackoverflow.com/questions/25252558/javafx-how-to-make-enter-key-submit-textarea/25252616
        partsSearchBox.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                onSearchPart(event);
            }
        });

        //https://stackoverflow.com/questions/25252558/javafx-how-to-make-enter-key-submit-textarea/25252616
        productsSearchBox.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                onSearchProduct(event);
            }
        });

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //fixed formatting so price shows two decimal places
        //https://stackoverflow.com/questions/11412360/javafx-table-cell-formatting
        partPriceColumn.setCellFactory(tableCell -> new TableCell<Part, Double>() {

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item.doubleValue()));
                }
            }
        } );

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //fixed formatting so price shows two decimal places
        //https://stackoverflow.com/questions/11412360/javafx-table-cell-formatting
        productPriceColumn.setCellFactory(tableCell -> new TableCell<Product, Double>() {

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item.doubleValue()));
                }
            }
        } );

        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());

    }

}

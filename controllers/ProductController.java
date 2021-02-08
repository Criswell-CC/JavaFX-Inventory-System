package InventorySystem.controllers;

import InventorySystem.models.Inventory;
import InventorySystem.models.Part;
import InventorySystem.models.Product;
import InventorySystem.utilities.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * Abstract controller class to handle common logic (search bar and buttons) for both add product and modify product forms.
 *
 */

public abstract class ProductController {

    Product productToAddOrModify;

    @FXML
    Button addAssociatedPartButton;
    @FXML
    Button cancelButton;
    @FXML
    Button removeAssociatedPartButton;
    @FXML
    Button saveButton;

    @FXML
    Label mainLabel;

    @FXML
    TableView<Part> partsTable;
    @FXML
    TableView<Part> associatedPartsTable;

    @FXML
    TableColumn<Part, Integer> partIdColumn;
    @FXML
    TableColumn<Part, Integer> partInventoryLevelColumn;
    @FXML
    TableColumn<Part, String> partNameColumn;
    @FXML
    TableColumn<Part, Double> partPriceColumn;

    @FXML
    TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML
    TableColumn<Part, Integer> associatedPartInventoryLevelColumn;
    @FXML
    TableColumn<Part, String> associatedPartNameColumn;
    @FXML
    TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    TextField enteredName;
    @FXML
    TextField enteredInv;
    @FXML
    TextField enteredPrice;
    @FXML
    TextField enteredMax;
    @FXML
    TextField enteredMin;

    @FXML
    TextField partsSearchBox;

    ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    ObservableList<Part> updatedAssociatedParts = FXCollections.observableArrayList();

    /**
     * Handles add associated part button click by checking if associated part is selected and not already associated
     * with product before adding part to associated parts table.
     * @param event button click event.
     */
    public void onAddAssociatedPart(ActionEvent event) {

        if (partsTable.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to add");
            alert.showAndWait();

        }

        else {

            for (Part part : associatedParts) {
                if (part.getId() == partsTable.getSelectionModel().getSelectedItem().getId()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The selected part is already associated with this product");
                    alert.showAndWait();
                    return;
                }
            }

            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

            updatedAssociatedParts = FXCollections.observableArrayList(associatedParts);

            updatedAssociatedParts.add(selectedPart);
            associatedPartsTable.setItems(updatedAssociatedParts);

        }
    }

    /**
     * Handles remove associated part button click by checking if associated part is selected and showing confirmation
     * dialog before removing part from associated parts table.
     * @param event button click event.
     */
    public void onRemoveAssociatedPart(ActionEvent event) {

        if (associatedPartsTable.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();

        }

        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?", ButtonType.YES, ButtonType.CANCEL);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

                updatedAssociatedParts.remove(selectedPart);
                associatedPartsTable.setItems(updatedAssociatedParts);

            }
        }
    }

    /**
     * Abstract save button handler.
     * @param event button click event.
     * @return bool indicating success of input validation and saving data to Inventory object.
     */
    abstract boolean onSave(ActionEvent event);

    /**
     * Handles cancel button click by returning to main screen.
     * @param event button click event
     * @throws IOException
     */
    public void onCancel(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/mainForm.fxml"));

        root = loader.load();

        stage.setScene(new Scene(root, 1050, 500));
        stage.centerOnScreen();
        stage.show();

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
     * Common method to set button click handlers to UI components.
     */
    public void setHandlers() {

        partsSearchBox.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                onSearchPart(event);
            }
        });

        addAssociatedPartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onAddAssociatedPart(event);
            }
        });

        removeAssociatedPartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                onRemoveAssociatedPart(event);

            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (onSave(event)) {
                    try {
                        onCancel(event);
                    }
                    catch (IOException exception) {
                        System.out.println(exception.getLocalizedMessage());
                    }
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    onCancel(event);
                }

                catch (IOException exception) {
                    System.out.println(exception.getLocalizedMessage());
                }
            }
        });

        enteredName.setFocusTraversable(false);
        enteredInv.setFocusTraversable(false);
        enteredPrice.setFocusTraversable(false);
        enteredMax.setFocusTraversable(false);
        enteredMin.setFocusTraversable(false);

        partsSearchBox.setFocusTraversable(false);

    }
}

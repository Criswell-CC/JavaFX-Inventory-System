package InventorySystem.controllers;

import InventorySystem.models.*;

import InventorySystem.utilities.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.String.valueOf;

/**
 *
 * Concrete controller class to handle logic specific to modify product form.
 *
 */

public class modifyProductFormController extends ProductController {

    /**
     * Sets product selected in table.
     * @param product product to modify.
     */
    public void setProduct(Product product) {

        productToAddOrModify = product;
        associatedParts = productToAddOrModify.getAllAssociatedParts();

    }

    /**
     * Override of onCancel method with resetting associatedParts list so associated parts added don't persist after
     * cancel
     * @param event button click event
     * @throws IOException
     */
    @Override
    public void onCancel(ActionEvent event) throws IOException {

        associatedParts = productToAddOrModify.getAllAssociatedParts();
        associatedPartsTable.setItems(associatedParts);

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/mainForm.fxml"));

        root = loader.load();

        stage.setScene(new Scene(root, 1050, 500));
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * Handles save button click by validating all input fields and updating product information in Inventory object.
     * @param event button click event.
     * @return bool indicating result of input validation and save
     */
    public boolean onSave(ActionEvent event) {

        if (Validator.validateProductInput(enteredName.getText().trim(), enteredInv.getText().trim(),
                enteredPrice.getText().trim(), enteredMax.getText().trim(), enteredMin.getText().trim(),
                updatedAssociatedParts)) {

            int index = Inventory.getIndexProduct(productToAddOrModify.getId());

            Product productToAdd = new Product(productToAddOrModify.getId(), enteredName.getText(),
                    Double.parseDouble(enteredPrice.getText()), Integer.parseInt(enteredInv.getText()),
                    Integer.parseInt(enteredMin.getText()), Integer.parseInt(enteredMax.getText()));

            for (Part part : updatedAssociatedParts) {
                productToAdd.addAssociatedPart(part);
            }

            Inventory.updateProduct(index, productToAdd);

            return true;
        }

        return false;

    }

    /**
     * Internal method to set UI with product data called during initialize method.
     */
    private void setUI() {

        if (productToAddOrModify != null) {

            enteredName.setText(productToAddOrModify.getName());
            enteredInv.setText(new String(valueOf(productToAddOrModify.getStock())));
            enteredPrice.setText(String.format("%.2f", productToAddOrModify.getPrice()).toString());
            enteredMax.setText(new String(valueOf(productToAddOrModify.getMax())));
            enteredMin.setText(new String(valueOf(productToAddOrModify.getMin())));

            associatedPartsTable.setItems(associatedParts);

        }
    }

    /**
     * Override of standard JavaFX method to initialize table columns and label specific to form.
     */
    @FXML
    private void initialize() {

        mainLabel.setText("Modify Product");

        setHandlers();

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

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

        partsTable.setItems(Inventory.getAllParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //https://stackoverflow.com/questions/11412360/javafx-table-cell-formatting
        associatedPartPriceColumn.setCellFactory(tableCell -> new TableCell<Part, Double>() {

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

        setUI();

    }

}

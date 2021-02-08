package InventorySystem.controllers;

import InventorySystem.models.Inventory;
import InventorySystem.models.Part;
import InventorySystem.models.Product;

import InventorySystem.utilities.Validator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * Concrete controller class to handle logic specific to add product form.
 *
 */

public class addProductFormController extends ProductController {

    /**
     * Handles save button click by validating all input fields and updating product information in Inventory object.
     * @param event button click event.
     * @return bool indicating result of input validation and save
     */
    @Override
    public boolean onSave(ActionEvent event) {

        if (Validator.validateProductInput(enteredName.getText().trim(), enteredInv.getText().trim(),
                enteredPrice.getText().trim(), enteredMax.getText().trim(), enteredMin.getText().trim(),
                updatedAssociatedParts) && Validator.validateProductPartPrices(enteredPrice.getText().trim(), updatedAssociatedParts)
        ) {

            productToAddOrModify = new Product(Inventory.getNewProductID(), enteredName.getText(),
                    Double.parseDouble(enteredPrice.getText()), Integer.parseInt(enteredInv.getText()),
                    Integer.parseInt(enteredMin.getText()), Integer.parseInt(enteredMax.getText()));

            for (Part part : associatedParts) {

                productToAddOrModify.addAssociatedPart(part);

            }

            Inventory.addProduct(productToAddOrModify);

            return true;

        }

        return false;
    }

    /**
     * Override of standard JavaFX method to initialize table columns and label specific to form.
     */
    @FXML
    private void initialize() {

        mainLabel.setText("Add Product");

        setHandlers();

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable.setItems(Inventory.getAllParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}

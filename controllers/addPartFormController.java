package InventorySystem.controllers;

import InventorySystem.models.Inventory;
import InventorySystem.models.inHouse;
import InventorySystem.models.Outsourced;

import InventorySystem.utilities.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * Concrete controller class to handle logic specific to add part form.
 *
 */

public class addPartFormController extends PartController {

    @Override
    public boolean onSave(ActionEvent event) {

        if (inHouseRadioButton.isSelected() && Validator.validateInhouseInput(enteredName.getText().trim(),
                enteredInv.getText().trim(), enteredPrice.getText().trim(), enteredMax.getText().trim(),
                enteredMin.getText().trim(), enteredMachineIDorCompanyName.getText().trim())) {

            Inventory.addPart(new inHouse(Inventory.getNewPartID(), enteredName.getText().trim(),
                    Double.parseDouble(enteredPrice.getText().trim()),
                    Integer.parseInt(enteredInv.getText().trim()), Integer.parseInt(enteredMin.getText().trim()),
                    Integer.parseInt(enteredMax.getText().trim()),
                    Integer.parseInt(enteredMachineIDorCompanyName.getText().trim())));

            return true;

        }

        else if (outsourcedRadioButton.isSelected() && Validator.validateOutsourcedInput(enteredName.getText().trim(), enteredInv.getText().trim(),
                enteredPrice.getText().trim(), enteredMax.getText().trim(),
                enteredMin.getText().trim(), enteredMachineIDorCompanyName.getText().trim())) {

            Inventory.addPart(new Outsourced(Inventory.getNewPartID(), enteredName.getText().trim(),
                    Double.parseDouble(enteredPrice.getText().trim()), Integer.parseInt(enteredInv.getText().trim()),
                    Integer.parseInt(enteredMin.getText().trim()), Integer.parseInt(enteredMax.getText().trim()),
                    enteredMachineIDorCompanyName.getText().trim()));

            return true;

        }

        else {

            return false;

        }
    }

    @FXML
    private void initialize() {

        mainLabel.setText("Add Part");

        inHouseRadioButton.setSelected(true);
        machineOrCompanyLabel.setText("Machine ID");

        super.setHandlers();

    }

}

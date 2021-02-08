package InventorySystem.controllers;

import InventorySystem.models.Inventory;
import InventorySystem.models.Part;
import InventorySystem.models.inHouse;
import InventorySystem.models.Outsourced;

import InventorySystem.utilities.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static java.lang.String.valueOf;

/**
 *
 * Concrete controller class to handle logic specific to modify part form.
 *
 */

public class modifyPartFormController extends PartController {

    private Part partToModify;

    public void setPart(Part part) {

        this.partToModify = part;

    }

    private void setUI() {

        if (partToModify != null) {

            enteredName.setText(partToModify.getName());
            enteredInv.setText(new String(valueOf(partToModify.getStock())));
            enteredPrice.setText(String.format("%.2f", partToModify.getPrice()).toString());
            enteredMax.setText(new String(valueOf(partToModify.getMax())));
            enteredMin.setText(new String(valueOf(partToModify.getMin())));

            if (partToModify instanceof inHouse) {

                inHouseRadioButton.setSelected(true);
                enteredMachineIDorCompanyName.setText(new String(valueOf(((inHouse) partToModify).getMachineId())));

            }

            else {

                outsourcedRadioButton.setSelected(true);
                enteredMachineIDorCompanyName.setText(((Outsourced)partToModify).getCompanyName());

            }

        }
    }

    @Override
    public boolean onSave(ActionEvent event) {

        if (inHouseRadioButton.isSelected() && Validator.validateInhouseInput(enteredName.getText().trim(),
                enteredInv.getText().trim(), enteredPrice.getText().trim(), enteredMax.getText().trim(),
                enteredMin.getText().trim(), enteredMachineIDorCompanyName.getText().trim())) {

            int index = Inventory.getIndexPart(partToModify.getId());

            Inventory.updatePart(index, new inHouse(partToModify.getId(), enteredName.getText().trim(),
                    Double.parseDouble(enteredPrice.getText().trim()),
                    Integer.parseInt(enteredInv.getText().trim()), Integer.parseInt(enteredMin.getText().trim()),
                    Integer.parseInt(enteredMax.getText().trim()),
                    Integer.parseInt(enteredMachineIDorCompanyName.getText().trim())));

            return true;

        }

        else if (outsourcedRadioButton.isSelected() && Validator.validateOutsourcedInput(enteredName.getText().trim(), enteredInv.getText().trim(),
                enteredPrice.getText().trim(), enteredMax.getText().trim(),
                enteredMin.getText().trim(), enteredMachineIDorCompanyName.getText().trim())) {

            int index = Inventory.getIndexPart(partToModify.getId());

            Inventory.updatePart(index, new Outsourced(partToModify.getId(), enteredName.getText().trim(),
                    Double.parseDouble(enteredPrice.getText().trim()),
                    Integer.parseInt(enteredInv.getText().trim()), Integer.parseInt(enteredMin.getText().trim()),
                    Integer.parseInt(enteredMax.getText().trim()),
                    enteredMachineIDorCompanyName.getText().trim()));

            return true;

        }

        else {

            return false;

        }
    }

    @FXML
    private void initialize() {

        mainLabel.setText("Modify Part");

        setUI();
        super.setHandlers();

    }
}

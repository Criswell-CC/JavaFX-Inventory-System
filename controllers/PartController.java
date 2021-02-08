package InventorySystem.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * Abstract controller class to handle common button and toggle group logic for both add part and modify part forms.
 *
 */

public abstract class PartController {

    private String companyName = "";
    private String machineId = "";

    @FXML
    Button saveButton;
    @FXML
    Button cancelButton;

    @FXML
    Label mainLabel;
    @FXML
    Label machineOrCompanyLabel;

    @FXML
    RadioButton inHouseRadioButton;
    @FXML
    RadioButton outsourcedRadioButton;

    @FXML
    ToggleGroup toggleGroup;

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
    TextField enteredMachineIDorCompanyName;

    /**
     * Abstract save button handler.
     * @param event button click event.
     * @return bool indicating success of input validation and saving data to Inventory object.
     */
    @FXML
    abstract boolean onSave(ActionEvent event);

    /**
     * Handles cancel button click by returning to main screen.
     * @param event button click event.
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
     * Common method to set button click handlers to UI components and change listener for in-house/outsourced
     * toggle group.
     */
    void setHandlers() {

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {

                RadioButton selectedButton = (RadioButton)newValue.getToggleGroup().getSelectedToggle();

                if (Objects.equals("Outsourced", selectedButton.getText())) {

                    machineId = enteredMachineIDorCompanyName.getText();
                    machineOrCompanyLabel.setText("Company Name");
                    enteredMachineIDorCompanyName.setText(companyName);

                }

                else {

                    companyName = enteredMachineIDorCompanyName.getText();
                    machineOrCompanyLabel.setText("Machine ID");
                    enteredMachineIDorCompanyName.setText(machineId);

                }
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

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
    }
}

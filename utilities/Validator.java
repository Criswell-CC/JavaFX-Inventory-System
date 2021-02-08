package InventorySystem.utilities;

import InventorySystem.models.Part;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * Utility class with static methods for validating user input across forms.
 *
 */

public class Validator {

    //https://stackoverflow.com/questions/7036324/what-is-the-regex-for-any-positive-integer-excluding-0
    static String nonNegativeIntPattern = "^[0-9]\\d*$";

    //https://stackoverflow.com/questions/1547574/regex-for-prices
    static String pricePattern = "^((\\d{1,3}|\\s*){1})((\\,\\d{3}|\\d)*)(\\s*|\\.(\\d{1,2}))$";

    /**
     * Method to check whether a string is a non-negative integer.
     * @param integer string that may be a non-negative integer
     * @return bool indicating whether string is a non-negative integer
     */
    static public boolean validateNonNegativeInt(String integer) {

        if (integer.matches(nonNegativeIntPattern)) {
            return true;
        }

        return false;

    }

    /**
     * Method to validate if all input fields are filled out and whether all input fields have expected types for
     * in-house part objects. Also checks for minimum/maximum consistency.
     * @param name entered part name string
     * @param stock entered stock string
     * @param price entered price string
     * @param max entered max stock string
     * @param min entered min stock string
     * @param machineID entered machine ID string
     * @return bool indicating success of input validation
     */
    static public boolean validateInhouseInput(String name, String stock, String price, String max, String min,
                                            String machineID) {

        if (name.isEmpty() || stock.isEmpty() || price.isEmpty() || max.isEmpty() || max.isEmpty() || min.isEmpty() ||
            machineID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please gave each field a value");
            alert.showAndWait();
            return false;
        }

        if (!stock.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Inventory field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!price.matches(pricePattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Price field must be a valid price");
            alert.showAndWait();
            return false;

        }

        if (!max.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Max stock field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!min.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Min stock field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!machineID.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Machine ID must be a number");
            alert.showAndWait();
            return false;

        }

        if (Integer.parseInt(min) > Integer.parseInt(max)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Min stock cannot be greater than max stock");
            alert.showAndWait();
            return false;

        }

        return true;
    }

    /**
     * Method to validate if all input fields are filled out and whether all input fields have expected types for
     * outsourced part objects. Also checks for minimum/maximum consistency.
     * @param name entered part name string
     * @param stock entered stock string
     * @param price entered price string
     * @param max entered max stock string
     * @param min entered min stock string
     * @param companyName entered company name string
     * @return bool indicating success of input validation
     */
    static public boolean validateOutsourcedInput(String name, String stock, String price, String max, String min,
                                               String companyName) {

        if (name.isEmpty() || stock.isEmpty() || price.isEmpty() || max.isEmpty() || max.isEmpty() || min.isEmpty() ||
                companyName.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please gave each field a value");
            alert.showAndWait();
            return false;

        }

        if (!stock.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Inventory field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!price.matches(pricePattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Price field must be a valid price");
            alert.showAndWait();
            return false;

        }

        if (!max.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Max stock field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!min.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Min stock field must be a number");
            alert.showAndWait();
            return false;

        }

        if (Integer.parseInt(min) > Integer.parseInt(max)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Min stock cannot be greater than max stock");
            alert.showAndWait();
            return false;

        }

        if (Integer.parseInt(stock) < Integer.parseInt(min)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Available stock can't be less than minimum");
            alert.showAndWait();
            return false;

        }

        return true;
    }

    /**
     * Method to validate if all input fields are filled out and whether all input fields have expected types for
     * product objects. Also checks for minimum/maximum consistency.
     * @param name entered product name string
     * @param stock entered stock string
     * @param price entered price string
     * @param max entered max stock string
     * @param min entered min stock string
     * @return bool indicating success of input validation
     */
    static public boolean validateProductInput(String name, String stock, String price, String max, String min,
                                               ObservableList<Part> associatedParts) {

        if (name.isEmpty() || stock.isEmpty() || price.isEmpty() || max.isEmpty() || max.isEmpty() || min.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please gave each field a value.");
            alert.showAndWait();
            return false;

        }

        if (!stock.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Inventory field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!price.matches(pricePattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Price field must be a valid price");
            alert.showAndWait();
            return false;

        }

        if (!max.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Max stock field must be a number");
            alert.showAndWait();
            return false;

        }

        if (!min.matches(nonNegativeIntPattern)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Min stock field must be a number");
            alert.showAndWait();
            return false;

        }

        if (Integer.parseInt(min) > Integer.parseInt(max)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Min stock cannot be greater than max stock");
            alert.showAndWait();
            return false;

        }

        if (associatedParts.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Product must have at least one associated part");
            alert.showAndWait();
            return false;

        }

        return true;

    }

    /**
     * Method to validate if product price is greater than price of individual associated parts and their sum.
     * @param productPrice entered product price
     * @param associatedParts list of all parts associated with a product
     * @return bool indicating result of price validation check
     */
    static public boolean validateProductPartPrices(String productPrice, ObservableList<Part> associatedParts) {

        int sum = 0;

        for (Part part : associatedParts) {

            if (Double.parseDouble(productPrice) < part.getPrice()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Product price can't be less than price of an associated part");
                alert.showAndWait();
                return false;

            }

            sum += part.getPrice();

        }

        if (Double.parseDouble(productPrice) < sum) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Product price can't be less than sum of prices of associated parts");
            alert.showAndWait();
            return false;

        }

        return true;

    }
}

package InventorySystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * Data model class for inventory system containing parts and products and serves as main data store for application.
 *
 */

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new part to allParts list.
     * @param newPart new part to add to inventory
     */
    public static void addPart(Part newPart) {

        allParts.add(newPart);

    }

    /**
     * Adds a new product to allProducts list.
     * @param newProduct new product to add to inventory
     */
    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);

    }

    /**
     * Searches all parts for part with given ID and returns part or null if not found.
     * @param partId ID of a specific part
     * @return part object with specified ID or null if none found
     */
    public static Part lookupPart(int partId) {

        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }

        return null;

    }

    /**
     * Searches all products for product with given ID and returns part or null if not found.
     * @param productId ID of a specific product
     * @return product object with specified ID or null if none found
     */
    public static Product lookupProduct(int productId) {

        for(Product product : allProducts) {
            if(product.getId() == productId) {
                return product;
            }
        }

        return null;

    }

    /**
     * Searches all parts for parts whose name contains passed search string and returns list with all parts.
     * @param partName string to search for.
     * @return list of all parts whose name property contains search string.
     */
    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> results = FXCollections.observableArrayList();

        if (partName.isEmpty() || partName == null) {
            return null;
        }

        for (Part part : allParts) {
            if (Objects.equals(partName, part.getName()))
                results.add(part);
        }

        return results;

    }

    /**
     * Searches all products for products whose name contains passed search string and returns list with all products.
     * @param productName string to search for.
     * @return list of all products whose name property contains search string.
     */
    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> results = FXCollections.observableArrayList();

        if (productName.isEmpty() || productName == null) {
            return null;
        }

        for (Product product : allProducts) {
            if (Objects.equals(productName, product.getName()))
                results.add(product);
        }

        return results;

    }

    /**
     * Resets selected part in Inventory object with new information.
     * @param index index of part in allParts list.
     * @param selectedPart selected part to update.
     */
    public static void updatePart(int index, Part selectedPart) {

        allParts.set(index, selectedPart);

    }

    /**
     * Resets selected product in Inventory object with new information.
     * @param index index of product in allProducts list.
     * @param selectedProduct selected product to update.
     */
    public static void updateProduct(int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);

    }

    /**
     * Deletes selected part from Inventory object.
     * @param selectedPart part to delete.
     * @return bool indicating success of remove operation.
     */
    public static boolean deletePart(Part selectedPart) {

        allParts.remove(selectedPart);

        return true;

    }

    /**
     * Deletes selected product from Inventory object.
     * @param selectedProduct product to delete.
     * @return bool indicating success of remove operation.
     */
    public static boolean deleteProduct(Product selectedProduct) {

        allProducts.remove(selectedProduct);

        return true;

    }

    /**
     * Accessor for allParts list of Inventory object.
     * @return list of all parts contained in Inventory object.
     */
    public static ObservableList<Part> getAllParts() {

        return allParts;

    }

    /**
     * Accessor for allProducts list of Inventory object.
     * @return list of all products contained in Inventory object.
     */
    public static ObservableList<Product> getAllProducts() {

        return allProducts;

    }

    /**
     * Calculates what ID a new part should have and returns that ID.
     * @return automatically generated ID for a new part.
     */
    public static int getNewPartID() {

        int lastIndex = allParts.size() - 1;

        int currentLastID = allParts.get(lastIndex).getId();

        return currentLastID+1;

    }

    /**
     * Calculates what ID a new product should have and returns that ID.
     * @return automatically generated ID for a new product.
     */
    public static int getNewProductID() {

        int lastIndex = allProducts.size() - 1;

        int currentLastID = allProducts.get(lastIndex).getId();

        return currentLastID+1;

    }

    /**
     * Calculates the index of a selected part in allParts list of Inventory object.
     * @param id part ID of selected part.
     * @return index of selected part in allParts list.
     */
    public static int getIndexPart(int id) {

        Part selectedPart = lookupPart(id);
        int index = allParts.indexOf(selectedPart);

        return index;

    }

    /**
     * Calculates the index of a selected part in allProducts list of Inventory object.
     * @param id product ID of selected product.
     * @return index of selected product in allProducts list.
     */
    public static int getIndexProduct(int id) {

        Product selectedProduct = lookupProduct(id);

        int index = allProducts.indexOf(selectedProduct);

        return index;

    }
}
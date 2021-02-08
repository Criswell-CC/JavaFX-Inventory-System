package InventorySystem.models;

/**
 * Data model class for product objects.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * @param id id of product
     * @param name name of product
     * @param price price of product
     * @param stock quantity of product in stock
     * @param min min number of product to keep in stock
     * @param max max number of product to keep in stock
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * @param part the associated part to add to the product
     */
    public void addAssociatedPart(Part part) {

        associatedParts.add(part);

    }

    /**
     *
     * @param selectedAssociatedPart associated part selected from parts table
     * @return bool value indicating whether delete was successful
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {

        int id = selectedAssociatedPart.getId();

        for(int i = 0; i < associatedParts.size(); i++) {

            if (associatedParts.get(i).getId() == id) {
                associatedParts.remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @return list of all associated parts for the product
     */
    public ObservableList<Part> getAllAssociatedParts() {

        return associatedParts;

    }
}

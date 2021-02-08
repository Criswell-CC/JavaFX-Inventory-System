package InventorySystem.models;

/**
 *
 * Concrete data model class for in-house parts.
 *
 */

public class inHouse extends Part {

    private int machineId;

    public inHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}

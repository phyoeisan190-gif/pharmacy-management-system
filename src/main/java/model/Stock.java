package model;

import java.sql.Timestamp;

public class Stock {

    private int stockId;
    private int medicineId;
    private int quantity;
    private int minimumQuantity;
    private Timestamp lastUpdated;

    public Stock() {
    }

    public Stock(int stockId, int medicineId,
                 int quantity, int minimumQuantity,
                 Timestamp lastUpdated) {

        this.stockId = stockId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
        this.lastUpdated = lastUpdated;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
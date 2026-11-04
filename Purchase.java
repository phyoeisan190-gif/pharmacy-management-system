package model;

import java.sql.Date;

public class Purchase {

    private int purchaseId;
    private int medicineId;
    private String supplierName;
    private int quantity;
    private double purchasePrice;
    private Date purchaseDate;

    public Purchase() {
    }

    public Purchase(int purchaseId, int medicineId,
                    String supplierName, int quantity,
                    double purchasePrice, Date purchaseDate) {

        this.purchaseId = purchaseId;
        this.medicineId = medicineId;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
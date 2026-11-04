package model;

import java.sql.Timestamp;

public class Sale {

    private int saleId;
    private Timestamp saleDate;
    private double totalAmount;

    public Sale() {
    }

    public Sale(int saleId, Timestamp saleDate,
                double totalAmount) {

        this.saleId = saleId;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
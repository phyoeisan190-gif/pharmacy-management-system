package model;

import java.sql.Date;

public class Medicine {

    private int medicineId;
    private int pharmacyId;
    private String medicineName;
    private String medicineType;
    private String company;
    private Date manufacturingDate;
    private Date expiryDate;
    private double purchasePrice;
    private double sellingPrice;

    public Medicine() {
    }

    public Medicine(int medicineId, int pharmacyId,
                    String medicineName,
                    String medicineType,
                    String company,
                    Date manufacturingDate,
                    Date expiryDate,
                    double purchasePrice,
                    double sellingPrice) {

        this.medicineId = medicineId;
        this.pharmacyId = pharmacyId;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.company = company;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
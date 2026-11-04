package controller;

import java.sql.Date;
import java.util.List;

import dao.MedicineDAO;
import model.Medicine;

public class MedicineController {

    private MedicineDAO medicineDAO;

    public MedicineController() {
        medicineDAO = new MedicineDAO();
    }

    // Add Medicine
    public boolean addMedicine(
            int pharmacyId,
            String medicineName,
            String medicineType,
            String company,
            Date manufacturingDate,
            Date expiryDate,
            double purchasePrice,
            double sellingPrice) {

        Medicine medicine = new Medicine();

        medicine.setPharmacyId(pharmacyId);
        medicine.setMedicineName(medicineName);
        medicine.setMedicineType(medicineType);
        medicine.setCompany(company);
        medicine.setManufacturingDate(manufacturingDate);
        medicine.setExpiryDate(expiryDate);
        medicine.setPurchasePrice(purchasePrice);
        medicine.setSellingPrice(sellingPrice);

        return medicineDAO.addMedicine(medicine);
    }


    // Update Medicine
    public boolean updateMedicine(
            int medicineId,
            int pharmacyId,
            String medicineName,
            String medicineType,
            String company,
            Date manufacturingDate,
            Date expiryDate,
            double purchasePrice,
            double sellingPrice) {

        Medicine medicine = new Medicine();

        medicine.setMedicineId(medicineId);
        medicine.setPharmacyId(pharmacyId);
        medicine.setMedicineName(medicineName);
        medicine.setMedicineType(medicineType);
        medicine.setCompany(company);
        medicine.setManufacturingDate(manufacturingDate);
        medicine.setExpiryDate(expiryDate);
        medicine.setPurchasePrice(purchasePrice);
        medicine.setSellingPrice(sellingPrice);

        return medicineDAO.updateMedicine(medicine);
    }


    // Delete Medicine
    public boolean deleteMedicine(int medicineId) {

        return medicineDAO.deleteMedicine(medicineId);
    }


    // Search Medicine
    public Medicine searchMedicine(int medicineId) {

        return medicineDAO.getMedicineById(medicineId);
    }


    // View All Medicines
    public List<Medicine> getAllMedicines() {

        return medicineDAO.getAllMedicines();
    }
}
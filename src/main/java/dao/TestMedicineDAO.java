package dao;

import java.sql.Date;
import java.util.List;

import model.Medicine;

public class TestMedicineDAO {

    public static void main(String[] args) {

        MedicineDAO dao = new MedicineDAO();

        // =========================
        // TEST 1 : ADD MEDICINE
        // =========================

        Medicine medicine = new Medicine();

        medicine.setPharmacyId(1);
        medicine.setMedicineName("Ibuprofen");
        medicine.setMedicineType("Tablet");
        medicine.setCompany("Test Pharma");
        medicine.setManufacturingDate(
                Date.valueOf("2025-01-01"));
        medicine.setExpiryDate(
                Date.valueOf("2027-01-01"));
        medicine.setPurchasePrice(60.00);
        medicine.setSellingPrice(90.00);

        boolean added = dao.addMedicine(medicine);

        if (added) {
            System.out.println("ADD SUCCESS");
        } else {
            System.out.println("ADD FAILED");
        }


        // =========================
        // TEST 2 : VIEW ALL
        // =========================

        System.out.println("\n--- MEDICINE LIST ---");

        List<Medicine> list = dao.getAllMedicines();

        for (Medicine m : list) {

            System.out.println(
                    m.getMedicineId()
                    + " | "
                    + m.getMedicineName()
                    + " | "
                    + m.getMedicineType()
                    + " | "
                    + m.getCompany()
                    + " | "
                    + m.getSellingPrice()
            );
        }


        // =========================
        // TEST 3 : SEARCH BY ID
        // =========================

        Medicine result = dao.getMedicineById(4);

        if (result != null) {

            System.out.println("\n--- SEARCH RESULT ---");

            System.out.println(
                    "ID       : "
                    + result.getMedicineId());

            System.out.println(
                    "Name     : "
                    + result.getMedicineName());

            System.out.println(
                    "Company  : "
                    + result.getCompany());

            System.out.println(
                    "Sell Price : "
                    + result.getSellingPrice());

        } else {

            System.out.println(
                    "\nMedicine not found.");
            
            
        }
        
        Medicine updateMedicine = dao.getMedicineById(4);

        if (updateMedicine != null) {

            System.out.println("Before Update: "
                    + updateMedicine.getSellingPrice());

            updateMedicine.setSellingPrice(100.00);

            boolean updated =
                    dao.updateMedicine(updateMedicine);

            System.out.println("Update Result: " + updated);

            Medicine result1 = dao.getMedicineById(4);

            if (result1 != null) {
                System.out.println("After Update: "
                        + result1.getSellingPrice());
                
            }
            
        }
        
    }
}
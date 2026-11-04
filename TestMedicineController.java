package controller;

import java.sql.Date;
import java.util.List;

import model.Medicine;

public class TestMedicineController {

    public static void main(String[] args) {

        MedicineController controller =
                new MedicineController();

        // =========================
        // ADD MEDICINE
        // =========================

        boolean added = controller.addMedicine(
                1,
                "Cetirizine",
                "Tablet",
                "Test Pharma",
                Date.valueOf("2025-01-01"),
                Date.valueOf("2027-01-01"),
                40.00,
                60.00
        );

        System.out.println(
                "ADD RESULT : " + added
        );


        // =========================
        // VIEW ALL
        // =========================

        System.out.println("\n--- ALL MEDICINES ---");

        List<Medicine> medicines =
                controller.getAllMedicines();

        for (Medicine m : medicines) {

            System.out.println(
                    "ID = " + m.getMedicineId()
                    + " | Name = " + m.getMedicineName()
                    + " | Selling Price = "
                    + m.getSellingPrice()
            );
        }
    }
}
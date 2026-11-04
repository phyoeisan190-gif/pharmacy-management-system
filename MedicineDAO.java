package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Medicine;
import util.DBConnection;

public class MedicineDAO {

    // 1. Add Medicine
    public boolean addMedicine(Medicine medicine) {

        String sql = """
                INSERT INTO medicine
                (pharmacy_id, medicine_name, medicine_type, company,
                 manufacturing_date, expiry_date,
                 purchase_price, selling_price)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicine.getPharmacyId());
            ps.setString(2, medicine.getMedicineName());
            ps.setString(3, medicine.getMedicineType());
            ps.setString(4, medicine.getCompany());
            ps.setDate(5, medicine.getManufacturingDate());
            ps.setDate(6, medicine.getExpiryDate());
            ps.setDouble(7, medicine.getPurchasePrice());
            ps.setDouble(8, medicine.getSellingPrice());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // 2. Update Medicine
    public boolean updateMedicine(Medicine medicine) {

        String sql = """
                UPDATE medicine
                SET pharmacy_id = ?,
                    medicine_name = ?,
                    medicine_type = ?,
                    company = ?,
                    manufacturing_date = ?,
                    expiry_date = ?,
                    purchase_price = ?,
                    selling_price = ?
                WHERE medicine_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicine.getPharmacyId());
            ps.setString(2, medicine.getMedicineName());
            ps.setString(3, medicine.getMedicineType());
            ps.setString(4, medicine.getCompany());
            ps.setDate(5, medicine.getManufacturingDate());
            ps.setDate(6, medicine.getExpiryDate());
            ps.setDouble(7, medicine.getPurchasePrice());
            ps.setDouble(8, medicine.getSellingPrice());
            ps.setInt(9, medicine.getMedicineId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // 3. Delete Medicine
    public boolean deleteMedicine(int medicineId) {

        String sql = """
                DELETE FROM medicine
                WHERE medicine_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicineId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // 4. Search Medicine by ID
    public Medicine getMedicineById(int medicineId) {

        String sql = """
                SELECT *
                FROM medicine
                WHERE medicine_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicineId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Medicine medicine = new Medicine();

                medicine.setMedicineId(
                        rs.getInt("medicine_id"));

                medicine.setPharmacyId(
                        rs.getInt("pharmacy_id"));

                medicine.setMedicineName(
                        rs.getString("medicine_name"));

                medicine.setMedicineType(
                        rs.getString("medicine_type"));
                
                medicine.setCompany(
                        rs.getString("company"));

                medicine.setManufacturingDate(
                        rs.getDate("manufacturing_date"));

                medicine.setExpiryDate(
                        rs.getDate("expiry_date"));

                medicine.setPurchasePrice(
                        rs.getDouble("purchase_price"));

                medicine.setSellingPrice(
                        rs.getDouble("selling_price"));

                return medicine;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // 5. View All Medicines
    public List<Medicine> getAllMedicines() {

        List<Medicine> medicineList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM medicine
                ORDER BY medicine_id
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Medicine medicine = new Medicine();

                medicine.setMedicineId(
                        rs.getInt("medicine_id"));

                medicine.setPharmacyId(
                        rs.getInt("pharmacy_id"));

                medicine.setMedicineName(
                        rs.getString("medicine_name"));

                medicine.setMedicineType(
                        rs.getString("medicine_type"));

                medicine.setCompany(
                        rs.getString("company"));

                medicine.setManufacturingDate(
                        rs.getDate("manufacturing_date"));

                medicine.setExpiryDate(
                        rs.getDate("expiry_date"));

                medicine.setPurchasePrice(
                        rs.getDouble("purchase_price"));

                medicine.setSellingPrice(
                        rs.getDouble("selling_price"));

                medicineList.add(medicine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return medicineList;
    }
}
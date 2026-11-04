package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Purchase;
import util.DBConnection;

public class PurchaseDAO {

    // =========================
    // ADD PURCHASE
    // =========================

    public boolean addPurchase(
            int medicineId,
            String supplierName,
            int quantity,
            double purchasePrice,
            java.sql.Date purchaseDate) {

        String sql =
                "INSERT INTO purchase " +
                "(medicine_id, supplier_name, quantity, purchase_price, purchase_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, medicineId);
            ps.setString(2, supplierName);
            ps.setInt(3, quantity);
            ps.setDouble(4, purchasePrice);
            ps.setDate(5, purchaseDate);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // UPDATE PURCHASE
    // =========================
    public boolean updatePurchase(
            int purchaseId,
            int medicineId,
            String supplierName,
            int quantity,
            double purchasePrice,
            java.sql.Date purchaseDate) {

        String sql =
                "UPDATE purchase SET " +
                "medicine_id = ?, " +
                "supplier_name = ?, " +
                "quantity = ?, " +
                "purchase_price = ?, " +
                "purchase_date = ? " +
                "WHERE purchase_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, medicineId);
            ps.setString(2, supplierName);
            ps.setInt(3, quantity);
            ps.setDouble(4, purchasePrice);
            ps.setDate(5, purchaseDate);
            ps.setInt(6, purchaseId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // DELETE PURCHASE
    // =========================
    public boolean deletePurchase(int purchaseId) {

        String sql =
                "DELETE FROM purchase " +
                "WHERE purchase_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, purchaseId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // SEARCH PURCHASE
    // =========================
    public Purchase getPurchaseById(int purchaseId) {

        String sql =
                "SELECT * FROM purchase " +
                "WHERE purchase_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, purchaseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Purchase purchase =
                        new Purchase();

                purchase.setPurchaseId(
                        rs.getInt("purchase_id")
                );

                purchase.setMedicineId(
                        rs.getInt("medicine_id")
                );

                purchase.setSupplierName(
                        rs.getString("supplier_name")
                );

                purchase.setQuantity(
                        rs.getInt("quantity")
                );

                purchase.setPurchasePrice(
                        rs.getDouble("purchase_price")
                );

                purchase.setPurchaseDate(
                        rs.getDate("purchase_date")
                );

                return purchase;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // GET ALL PURCHASES
    // =========================
    public List<Purchase> getAllPurchases() {

        List<Purchase> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM purchase";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Purchase purchase =
                        new Purchase();

                purchase.setPurchaseId(
                        rs.getInt("purchase_id")
                );

                purchase.setMedicineId(
                        rs.getInt("medicine_id")
                );

                purchase.setSupplierName(
                        rs.getString("supplier_name")
                );

                purchase.setQuantity(
                        rs.getInt("quantity")
                );

                purchase.setPurchasePrice(
                        rs.getDouble("purchase_price")
                );

                purchase.setPurchaseDate(
                        rs.getDate("purchase_date")
                );

                list.add(purchase);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}
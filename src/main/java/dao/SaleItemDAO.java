package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.SaleItem;
import util.DBConnection;

public class SaleItemDAO {

    // =========================
    // ADD SALE ITEM
    // =========================

    public boolean addSaleItem(
            int saleId,
            int medicineId,
            int quantity,
            double unitPrice,
            double subtotal) {

        String sql =
                "INSERT INTO sale_item " +
                "(sale_id, medicine_id, quantity, unit_price, subtotal) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, saleId);
            ps.setInt(2, medicineId);
            ps.setInt(3, quantity);
            ps.setDouble(4, unitPrice);
            ps.setDouble(5, subtotal);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // UPDATE SALE ITEM
    // =========================

    public boolean updateSaleItem(
            int saleItemId,
            int saleId,
            int medicineId,
            int quantity,
            double unitPrice,
            double subtotal) {

        String sql =
                "UPDATE sale_item SET " +
                "sale_id = ?, " +
                "medicine_id = ?, " +
                "quantity = ?, " +
                "unit_price = ?, " +
                "subtotal = ? " +
                "WHERE sale_item_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, saleId);
            ps.setInt(2, medicineId);
            ps.setInt(3, quantity);
            ps.setDouble(4, unitPrice);
            ps.setDouble(5, subtotal);
            ps.setInt(6, saleItemId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // DELETE SALE ITEM
    // =========================

    public boolean deleteSaleItem(int saleItemId) {

        String sql =
                "DELETE FROM sale_item " +
                "WHERE sale_item_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, saleItemId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // SEARCH SALE ITEM
    // =========================

    public SaleItem getSaleItemById(int saleItemId) {

        String sql =
                "SELECT * FROM sale_item " +
                "WHERE sale_item_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, saleItemId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                SaleItem item = new SaleItem();

                item.setSaleItemId(
                        rs.getInt("sale_item_id")
                );

                item.setSaleId(
                        rs.getInt("sale_id")
                );

                item.setMedicineId(
                        rs.getInt("medicine_id")
                );

                item.setQuantity(
                        rs.getInt("quantity")
                );

                item.setUnitPrice(
                        rs.getDouble("unit_price")
                );
                item.setSubtotal(
                        rs.getDouble("subtotal")
                );

                return item;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // GET SALE ITEMS BY SALE ID
    // =========================

    public List<SaleItem> getSaleItemsBySaleId(
            int saleId) {

        List<SaleItem> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM sale_item " +
                "WHERE sale_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, saleId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SaleItem item = new SaleItem();

                item.setSaleItemId(
                        rs.getInt("sale_item_id")
                );

                item.setSaleId(
                        rs.getInt("sale_id")
                );

                item.setMedicineId(
                        rs.getInt("medicine_id")
                );

                item.setQuantity(
                        rs.getInt("quantity")
                );

                item.setUnitPrice(
                        rs.getDouble("unit_price")
                );

                item.setSubtotal(
                        rs.getDouble("subtotal")
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // GET ALL SALE ITEMS
    // =========================

    public List<SaleItem> getAllSaleItems() {

        List<SaleItem> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM sale_item";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SaleItem item = new SaleItem();

                item.setSaleItemId(
                        rs.getInt("sale_item_id")
                );

                item.setSaleId(
                        rs.getInt("sale_id")
                );

                item.setMedicineId(
                        rs.getInt("medicine_id")
                );

                item.setQuantity(
                        rs.getInt("quantity")
                );

                item.setUnitPrice(
                        rs.getDouble("unit_price")
                );

                item.setSubtotal(
                        rs.getDouble("subtotal")
                );

                list.add(item);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}
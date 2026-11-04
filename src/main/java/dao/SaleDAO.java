package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Sale;
import util.DBConnection;

public class SaleDAO {

    // =========================
    // ADD SALE
    // =========================
    public boolean addSale(double totalAmount) {

        String sql = "INSERT INTO sale (total_amount) "
                   + "VALUES (?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, totalAmount);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // UPDATE SALE
    // =========================
    public boolean updateSale(
            int saleId,
            double totalAmount) {

        String sql = "UPDATE sale SET "
                   + "total_amount = ? "
                   + "WHERE sale_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, totalAmount);
            ps.setInt(2, saleId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // DELETE SALE
    // =========================
    public boolean deleteSale(int saleId) {

        String sql = "DELETE FROM sale "
                   + "WHERE sale_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, saleId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // SEARCH SALE
    // =========================
    public Sale getSaleById(int saleId) {

        String sql = "SELECT * FROM sale "
                   + "WHERE sale_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, saleId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Sale sale = new Sale();

                sale.setSaleId(
                    rs.getInt("sale_id")
                );

                sale.setSaleDate(
                    rs.getTimestamp("sale_date")
                );

                sale.setTotalAmount(
                    rs.getDouble("total_amount")
                );

                return sale;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // GET ALL SALES
    // =========================
    public List<Sale> getAllSales() {

        List<Sale> list = new ArrayList<>();

        String sql = "SELECT * FROM sale";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Sale sale = new Sale();

                sale.setSaleId(
                    rs.getInt("sale_id")
                );

                sale.setSaleDate(
                    rs.getTimestamp("sale_date")
                );

                sale.setTotalAmount(
                    rs.getDouble("total_amount")
                );

                list.add(sale);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
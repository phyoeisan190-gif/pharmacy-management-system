package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Stock;
import util.DBConnection;

public class StockDAO {

    // =====================================================
    // ADD STOCK
    // =====================================================
    public boolean addStock(int stockId, int medicineId, int quantity) {

        String sql =
                "INSERT INTO stock " +
                "(stock_id, medicine_id, quantity, minimum_quantity) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockId);
            ps.setInt(2, medicineId);
            ps.setInt(3, quantity);
            ps.setInt(4, 10);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // UPDATE STOCK
    // =====================================================
    public boolean updateStock(
            int stockId,
            int medicineId,
            int quantity,
            int minimumQuantity) {

        String sql =
                "UPDATE stock SET " +
                "medicine_id = ?, " +
                "quantity = ?, " +
                "minimum_quantity = ? " +
                "WHERE stock_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicineId);
            ps.setInt(2, quantity);
            ps.setInt(3, minimumQuantity);
            ps.setInt(4, stockId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // DELETE STOCK
    // =====================================================
    public boolean deleteStock(int stockId) {

        String sql =
                "DELETE FROM stock WHERE stock_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // GET STOCK BY ID
    // =====================================================
    public Stock getStockById(int stockId) {

        String sql =
                "SELECT * FROM stock WHERE stock_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Stock stock = new Stock();

                    stock.setStockId(
                            rs.getInt("stock_id")
                    );

                    stock.setMedicineId(
                            rs.getInt("medicine_id")
                    );

                    stock.setQuantity(
                            rs.getInt("quantity")
                    );

                    stock.setMinimumQuantity(
                            rs.getInt("minimum_quantity")
                    );

                    stock.setLastUpdated(
                            rs.getTimestamp("last_updated")
                    );

                    return stock;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // GET ALL STOCK
    // =====================================================
    public List<Stock> getAllStocks() {

    	List<Stock> list = new ArrayList<>();

        String sql =
                "SELECT * FROM stock ORDER BY stock_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Stock stock = new Stock();

                stock.setStockId(
                        rs.getInt("stock_id")
                );

                stock.setMedicineId(
                        rs.getInt("medicine_id")
                );

                stock.setQuantity(
                        rs.getInt("quantity")
                );

                stock.setMinimumQuantity(
                        rs.getInt("minimum_quantity")
                );

                stock.setLastUpdated(
                        rs.getTimestamp("last_updated")
                );

                list.add(stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // GET LOW STOCK
    // =====================================================
    public List<Stock> getLowStock() {

        List<Stock> list = new ArrayList<>();

        String sql =
                "SELECT * FROM stock " +
                "WHERE quantity <= minimum_quantity " +
                "ORDER BY quantity ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Stock stock = new Stock();

                stock.setStockId(
                        rs.getInt("stock_id")
                );

                stock.setMedicineId(
                        rs.getInt("medicine_id")
                );

                stock.setQuantity(
                        rs.getInt("quantity")
                );

                stock.setMinimumQuantity(
                        rs.getInt("minimum_quantity")
                );

                stock.setLastUpdated(
                        rs.getTimestamp("last_updated")
                );

                list.add(stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // GET CURRENT QUANTITY
    // =====================================================
    public int getCurrentQuantity(int medicineId) {

        String sql =
                "SELECT quantity FROM stock " +
                "WHERE medicine_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicineId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }


    // =====================================================
    // ADD STOCK HISTORY
    // =====================================================
    public boolean addStockHistory(
            int medicineId,
            String transactionType,
            int quantityChange,
            int stockBefore,
            int stockAfter) {

        String sql =
                "INSERT INTO stock_history " +
                "(medicine_id, transaction_type, quantity_change, " +
                "stock_before, stock_after) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicineId);
            ps.setString(2, transactionType);
            ps.setInt(3, quantityChange);
            ps.setInt(4, stockBefore);
            ps.setInt(5, stockAfter);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // PURCHASE
    // INCREASE STOCK
    // =====================================================
    public boolean increaseStock(
            int medicineId,
            int quantity) {

        if (quantity <= 0) {
            return false;
        }

        String sql =
                "UPDATE stock " +
                "SET quantity = quantity + ? " +
                "WHERE medicine_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, medicineId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // SALE
    // DECREASE STOCK
    // =====================================================
    public boolean decreaseStock(
            int medicineId,
            int quantity) {

        if (quantity <= 0) {
            return false;
        }

        String sql =
                "UPDATE stock " +
                "SET quantity = quantity - ? " +
                "WHERE medicine_id = ? " +
                "AND quantity >= ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, medicineId);
            ps.setInt(3, quantity);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // PURCHASE
    // INCREASE STOCK + SAVE HISTORY
    // =====================================================
    public boolean increaseStockWithHistory(
            int medicineId,
            int quantity) {

        if (quantity <= 0) {
            return false;
        }

        int stockBefore =
                getCurrentQuantity(medicineId);

        if (stockBefore < 0) {
            return false;
        }

        boolean updated =
                increaseStock(
                        medicineId,
                        quantity
                );

        if (!updated) {
            return false;
        }

        int stockAfter =
                getCurrentQuantity(medicineId);

        if (stockAfter < 0) {
            return false;
        }

        return addStockHistory(
                medicineId,
                "PURCHASE",
                quantity,
                stockBefore,
                stockAfter
        );
    }


    // =====================================================
    // SALE
    // DECREASE STOCK + SAVE HISTORY
    // =====================================================
    public boolean decreaseStockWithHistory(
            int medicineId,
            int quantity) {

        if (quantity <= 0) {
            return false;
        }

        int stockBefore =
                getCurrentQuantity(medicineId);

        if (stockBefore < 0) {
            return false;
        }

        // Not enough stock
        if (stockBefore < quantity) {
            return false;
        }

        boolean updated =
                decreaseStock(
                        medicineId,
                        quantity
                );

        if (!updated) {
            return false;
        }

        int stockAfter =
                getCurrentQuantity(medicineId);

        if (stockAfter < 0) {
            return false;
        }

        return addStockHistory(
                medicineId,
                "SALE",
                -quantity,
                stockBefore,
                stockAfter
        );
    }
 // =====================================================
    // GET STOCK HISTORY
    // =====================================================
    public List<String[]> getStockHistory() {

        List<String[]> list =
                new ArrayList<>();

        String sql =
                "SELECT history_id, medicine_id, " +
                "transaction_type, quantity_change, " +
                "stock_before, stock_after, transaction_date " +
                "FROM stock_history " +
                "ORDER BY history_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String[] row = new String[7];

                row[0] =
                        String.valueOf(
                                rs.getInt("history_id")
                        );

                row[1] =
                        String.valueOf(
                                rs.getInt("medicine_id")
                        );

                row[2] =
                        rs.getString(
                                "transaction_type"
                        );

                row[3] =
                        String.valueOf(
                                rs.getInt("quantity_change")
                        );

                row[4] =
                        String.valueOf(
                                rs.getInt("stock_before")
                        );

                row[5] =
                        String.valueOf(
                                rs.getInt("stock_after")
                        );

                row[6] =
                        String.valueOf(
                                rs.getTimestamp(
                                        "transaction_date"
                                )
                        );

                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

    
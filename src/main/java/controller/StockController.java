package controller;

import java.util.List;

import dao.StockDAO;
import model.Stock;

public class StockController {

    private StockDAO stockDAO;

    public StockController() {
        stockDAO = new StockDAO();
    }

    // =========================
    // ADD STOCK
    // =========================
    public boolean addStock(
            int medicineId,
            int quantity,
            int minimumQuantity) {

        return stockDAO.addStock(
                medicineId,
                quantity,
                minimumQuantity
        );
    }

    // =========================
    // UPDATE STOCK
    // =======================
    public boolean updateStock(
            int stockId,
            int medicineId,
            int quantity,
            int minimumQuantity) {

        return stockDAO.updateStock(
                stockId,
                medicineId,
                quantity,
                minimumQuantity
        );
    }

    // =========================
    // DELETE STOCK
    // =========================
    public boolean deleteStock(int stockId) {

        return stockDAO.deleteStock(stockId);
    }

    // =========================
    // SEARCH STOCK
    // =========================

    public Stock searchStock(int stockId) {

        return stockDAO.getStockById(stockId);
    }

    // =========================
    // GET ALL STOCK
    // =========================

    public List<Stock> getAllStocks() {

        return stockDAO.getAllStocks();
    }

    // =========================
    // LOW STOCK
    // =========================
    public List<Stock> getLowStock() {

        return stockDAO.getLowStock();
    }
}
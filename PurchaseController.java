package controller;

import java.sql.Date;
import java.util.List;

import dao.PurchaseDAO;
import dao.StockDAO;
import model.Purchase;

public class PurchaseController {

    private PurchaseDAO purchaseDAO;
    private StockDAO stockDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================
    public PurchaseController() {

        purchaseDAO = new PurchaseDAO();
        stockDAO = new StockDAO();
    }


    // =====================================================
    // ADD PURCHASE
    // PURCHASE → STOCK INCREASE → HISTORY
    // =====================================================
    public boolean addPurchase(
            int medicineId,
            String supplierName,
            int quantity,
            double purchasePrice,
            Date purchaseDate) {

        if (medicineId <= 0) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }

        if (purchasePrice < 0) {
            return false;
        }

        // Save Purchase
        boolean purchaseResult =
                purchaseDAO.addPurchase(
                        medicineId,
                        supplierName,
                        quantity,
                        purchasePrice,
                        purchaseDate
                );

        if (!purchaseResult) {
            return false;
        }

        // Increase Stock + Save History
        boolean stockResult =
                stockDAO.increaseStockWithHistory(
                        medicineId,
                        quantity
                );

        if (!stockResult) {
            return false;
        }

        return true;
    }


    // =====================================================
    // UPDATE PURCHASE
    // =====================================================
    public boolean updatePurchase(
            int purchaseId,
            int medicineId,
            String supplierName,
            int quantity,
            double purchasePrice,
            Date purchaseDate) {

        return purchaseDAO.updatePurchase(
                purchaseId,
                medicineId,
                supplierName,
                quantity,
                purchasePrice,
                purchaseDate
        );
    }


    // =====================================================
    // DELETE PURCHASE
    // =====================================================
    public boolean deletePurchase(int purchaseId) {

        return purchaseDAO.deletePurchase(
                purchaseId
        );
    }


    // =====================================================
    // SEARCH PURCHASE
    // =====================================================
    public Purchase searchPurchase(int purchaseId) {

        return purchaseDAO.getPurchaseById(
                purchaseId
        );
    }


    // =====================================================
    // GET ALL PURCHASES
    // =====================================================
    public List<Purchase> getAllPurchases() {

        return purchaseDAO.getAllPurchases();
    }
}

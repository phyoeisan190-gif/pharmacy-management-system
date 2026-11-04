package controller;

import java.util.List;

import dao.SaleDAO;
import dao.SaleItemDAO;
import dao.StockDAO;

import model.Sale;
import model.SaleItem;

public class SaleController {

    private SaleDAO saleDAO;
    private SaleItemDAO saleItemDAO;
    private StockDAO stockDAO;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================
    public SaleController() {

        saleDAO = new SaleDAO();
        saleItemDAO = new SaleItemDAO();
        stockDAO = new StockDAO();
    }


    // =====================================================
    // ADD SALE
    // =====================================================
    public boolean addSale(double totalAmount) {

        return saleDAO.addSale(
                totalAmount
        );
    }


    // =====================================================
    // UPDATE SALE
    // =====================================================
    public boolean updateSale(
            int saleId,
            double totalAmount) {

        return saleDAO.updateSale(
                saleId,
                totalAmount
        );
    }


    // =====================================================
    // DELETE SALE
    // =====================================================
    public boolean deleteSale(int saleId) {

        return saleDAO.deleteSale(
                saleId
        );
    }


    // =====================================================
    // SEARCH SALE
    // =====================================================
    public Sale searchSale(int saleId) {

        return saleDAO.getSaleById(
                saleId
        );
    }


    // =====================================================
    // GET ALL SALES
    // =====================================================
    public List<Sale> getAllSales() {

        return saleDAO.getAllSales();
    }


    // =====================================================
    // ADD SALE ITEM
    // SALE → STOCK DECREASE → HISTORY
    // =====================================================
    public boolean addSaleItem(
            int saleId,
            int medicineId,
            int quantity,
            double unitPrice,
            double subtotal) {

        if (saleId <= 0) {
            return false;
        }

        if (medicineId <= 0) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }

        if (unitPrice < 0) {
            return false;
        }

        // =================================================
        // FIRST CHECK STOCK
        // =================================================
        int currentStock =
                stockDAO.getCurrentQuantity(
                        medicineId
                );

        if (currentStock < 0) {
            return false;
        }

        if (currentStock < quantity) {
            return false;
        }


        // =================================================
        // SAVE SALE ITEM
        // =================================================
        boolean result =
                saleItemDAO.addSaleItem(
                        saleId,
                        medicineId,
                        quantity,
                        unitPrice,
                        subtotal
                );

        if (!result) {
            return false;
        }


        // =================================================
        // DECREASE STOCK + SAVE HISTORY
        // =================================================
        boolean stockResult =
                stockDAO.decreaseStockWithHistory(
                        medicineId,
                        quantity
                );

        if (!stockResult) {
            return false;
        }

        return true;
    }
 // =====================================================
    // UPDATE SALE ITEM
    // =====================================================
    public boolean updateSaleItem(
            int saleItemId,
            int saleId,
            int medicineId,
            int quantity,
            double unitPrice,
            double subtotal) {

        return saleItemDAO.updateSaleItem(
                saleItemId,
                saleId,
                medicineId,
                quantity,
                unitPrice,
                subtotal
        );
    }


    // =====================================================
    // DELETE SALE ITEM
    // =====================================================
    public boolean deleteSaleItem(
            int saleItemId) {

        return saleItemDAO.deleteSaleItem(
                saleItemId
        );
    }


    // =====================================================
    // SEARCH SALE ITEM
    // =====================================================
    public SaleItem searchSaleItem(
            int saleItemId) {

        return saleItemDAO.getSaleItemById(
                saleItemId
        );
    }


    // =====================================================
    // GET SALE ITEMS BY SALE ID
    // =====================================================
    public List<SaleItem> getSaleItemsBySaleId(
            int saleId) {

        return saleItemDAO.getSaleItemsBySaleId(
                saleId
        );
    }


    // =====================================================
    // GET ALL SALE ITEMS
    // =====================================================
    public List<SaleItem> getAllSaleItems() {

        return saleItemDAO.getAllSaleItems();
    }


    // =====================================================
    // DECREASE STOCK
    // =====================================================
    public boolean decreaseStock(
            int medicineId,
            int quantity) {

        return stockDAO.decreaseStockWithHistory(
                medicineId,
                quantity
        );
    }
}

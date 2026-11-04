package model;

public class LowStockMedicine {

    private int lowStockId;
    private int medicineId;
    private int currentQty;
    private int minimumQty;

    public LowStockMedicine() {
    }

    public LowStockMedicine(int lowStockId,
                            int medicineId,
                            int currentQty,
                            int minimumQty) {

        this.lowStockId = lowStockId;
        this.medicineId = medicineId;
        this.currentQty = currentQty;
        this.minimumQty = minimumQty;
    }

    public int getLowStockId() {
        return lowStockId;
    }

    public void setLowStockId(int lowStockId) {
        this.lowStockId = lowStockId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public int getMinimumQty() {
        return minimumQty;
    }

    public void setMinimumQty(int minimumQty) {
        this.minimumQty = minimumQty;
    }
}
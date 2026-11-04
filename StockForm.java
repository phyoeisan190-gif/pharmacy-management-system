package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.StockController;
import model.Stock;

public class StockForm extends JFrame {

    private JTextField txtStockId;
    private JTextField txtMedicineId;
    private JTextField txtQuantity;
    private JTextField txtMinimumQuantity;
    private JTextField txtLastUpdated;

    private JTable table;
    private DefaultTableModel tableModel;

    private StockController controller;

    // Dashboard
    private MainForm mainForm;

    // =====================================================
    // DEFAULT CONSTRUCTOR
    // =====================================================

    public StockForm() {

        controller = new StockController();

        setTitle("Stock Management");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadStocks();
    }

    // =====================================================
    // CONSTRUCTOR FROM MAIN DASHBOARD
    // =====================================================

    public StockForm(MainForm mainForm) {

        this.mainForm = mainForm;

        controller = new StockController();

        setTitle("Stock Management");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadStocks();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        // =================================================
        // INPUT PANEL
        // =================================================

        JPanel inputPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                5,
                                5
                        )
                );

        txtStockId =
                new JTextField();

        txtMedicineId =
                new JTextField();

        txtQuantity =
                new JTextField();

        txtMinimumQuantity =
                new JTextField();

        txtLastUpdated =
                new JTextField();

        inputPanel.add(
                new JLabel("Stock ID")
        );

        inputPanel.add(
                txtStockId
        );

        inputPanel.add(
                new JLabel("Medicine ID")
        );

        inputPanel.add(
                txtMedicineId
        );

        inputPanel.add(
                new JLabel("Quantity")
        );

        inputPanel.add(
                txtQuantity
        );

        inputPanel.add(
                new JLabel("Minimum Quantity")
        );

        inputPanel.add(
                txtMinimumQuantity
        );

        inputPanel.add(
                new JLabel("Last Updated")
        );

        inputPanel.add(
                txtLastUpdated
        );

        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel();

        JButton btnAdd =
                new JButton("Add");

        JButton btnUpdate =
                new JButton("Update");

        JButton btnDelete =
                new JButton("Delete");
        JButton btnSearch =
                new JButton("Search");

        JButton btnClear =
                new JButton("Clear");

        JButton btnLowStock =
                new JButton("Low Stock");

        JButton btnBack =
                new JButton("← Back");

        buttonPanel.add(btnAdd);

        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnSearch);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnLowStock);

        buttonPanel.add(btnBack);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {

                "Stock ID",
                "Medicine ID",
                "Quantity",
                "Minimum Quantity",
                "Last Updated"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );

        table =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        btnAdd.addActionListener(
                e -> addStock()
        );

        btnUpdate.addActionListener(
                e -> updateStock()
        );

        btnDelete.addActionListener(
                e -> deleteStock()
        );

        btnSearch.addActionListener(
                e -> searchStock()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );

        btnLowStock.addActionListener(
                e -> showLowStock()
        );

        // =================================================
        // BACK BUTTON
        // =================================================

        btnBack.addActionListener(e -> {

            dispose();

            if (mainForm != null) {

                mainForm.setLocationRelativeTo(null);

                mainForm.setVisible(true);
            }
        });

        // =================================================
        // TABLE CLICK
        // =================================================

        table.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        int row =
                                table.getSelectedRow();

                        if (row >= 0) {

                            txtStockId.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );

                            txtMedicineId.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    1
                                            )
                                            .toString()
                            );

                            txtQuantity.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString()
                            );
                            txtMinimumQuantity.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    3
                                            )
                                            .toString()
                            );

                            txtLastUpdated.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    4
                                            )
                                            .toString()
                            );
                        }
                    }
                }
        );
    }

    // =====================================================
    // ADD STOCK
    // =====================================================

    private void addStock() {

        try {

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId.getText()
                    );

            int quantity =
                    Integer.parseInt(
                            txtQuantity.getText()
                    );

            int minimumQuantity =
                    Integer.parseInt(
                            txtMinimumQuantity.getText()
                    );

            boolean result =
                    controller.addStock(
                            medicineId,
                            quantity,
                            minimumQuantity
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock Added Successfully"
                );

                loadStocks();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Add Stock Failed"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data."
            );
        }
    }

    // =====================================================
    // UPDATE STOCK
    // =====================================================

    private void updateStock() {

        try {

            int stockId =
                    Integer.parseInt(
                            txtStockId.getText()
                    );

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId.getText()
                    );

            int quantity =
                    Integer.parseInt(
                            txtQuantity.getText()
                    );

            int minimumQuantity =
                    Integer.parseInt(
                            txtMinimumQuantity.getText()
                    );

            boolean result =
                    controller.updateStock(
                            stockId,
                            medicineId,
                            quantity,
                            minimumQuantity
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock Updated Successfully"
                );

                loadStocks();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Stock Failed"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data."
            );
        }
    }

    // =====================================================
    // DELETE STOCK
    // =====================================================
    private void deleteStock() {

        try {

            int stockId =
                    Integer.parseInt(
                            txtStockId.getText()
                    );

            int answer =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (
                    answer ==
                    JOptionPane.YES_OPTION
            ) {

                boolean result =
                        controller.deleteStock(
                                stockId
                        );

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Stock Deleted Successfully"
                    );

                    loadStocks();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete Stock Failed"
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a stock first."
            );
        }
    }

    // =====================================================
    // SEARCH STOCK
    // =====================================================

    private void searchStock() {

        try {

            int stockId =
                    Integer.parseInt(
                            txtStockId.getText()
                    );

            Stock stock =
                    controller.searchStock(
                            stockId
                    );

            if (stock != null) {

                txtMedicineId.setText(
                        String.valueOf(
                                stock.getMedicineId()
                        )
                );

                txtQuantity.setText(
                        String.valueOf(
                                stock.getQuantity()
                        )
                );

                txtMinimumQuantity.setText(
                        String.valueOf(
                                stock.getMinimumQuantity()
                        )
                );

                Timestamp timestamp =
                        stock.getLastUpdated();

                if (timestamp != null) {

                    txtLastUpdated.setText(
                            timestamp.toString()
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock Not Found"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid Stock ID."
            );
        }
    }

    // =====================================================
    // LOAD STOCKS
    // =====================================================

    private void loadStocks() {

        tableModel.setRowCount(0);

        List<Stock> list =
                controller.getAllStocks();

        for (Stock stock : list) {

            tableModel.addRow(
                    new Object[] {

                            stock.getStockId(),

                            stock.getMedicineId(),

                            stock.getQuantity(),

                            stock.getMinimumQuantity(),

                            stock.getLastUpdated()
                    }
            );
        }
    }

    // =====================================================
    // SHOW LOW STOCK
    // =====================================================

    private void showLowStock() {

        tableModel.setRowCount(0);

        List<Stock> list =
                controller.getLowStock();

        for (Stock stock : list) {
        	tableModel.addRow(
                    new Object[] {

                            stock.getStockId(),

                            stock.getMedicineId(),

                            stock.getQuantity(),

                            stock.getMinimumQuantity(),

                            stock.getLastUpdated()
                    }
            );
        }

        JOptionPane.showMessageDialog(
                this,
                "Low Stock Medicines Loaded"
        );
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        txtStockId.setText("");

        txtMedicineId.setText("");

        txtQuantity.setText("");

        txtMinimumQuantity.setText("");

        txtLastUpdated.setText("");
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        StockForm form =
                new StockForm();

        form.setVisible(true);
    }
}
        
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;
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

import controller.PurchaseController;
import model.Purchase;

public class PurchaseForm extends JFrame {

    private JTextField txtPurchaseId;
    private JTextField txtMedicineId;
    private JTextField txtSupplierName;
    private JTextField txtQuantity;
    private JTextField txtPurchasePrice;
    private JTextField txtPurchaseDate;

    private JTable table;
    private DefaultTableModel tableModel;

    private PurchaseController controller;

    // Dashboard
    private MainForm mainForm;

    // =====================================================
    // DEFAULT CONSTRUCTOR
    // =====================================================

    public PurchaseForm() {

        controller = new PurchaseController();

        setTitle("Purchase Management");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadPurchases();
    }

    // =====================================================
    // CONSTRUCTOR FROM MAIN DASHBOARD
    // =====================================================

    public PurchaseForm(MainForm mainForm) {

        this.mainForm = mainForm;

        controller = new PurchaseController();

        setTitle("Purchase Management");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadPurchases();
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

        txtPurchaseId =
                new JTextField();

        txtMedicineId =
                new JTextField();

        txtSupplierName =
                new JTextField();

        txtQuantity =
                new JTextField();

        txtPurchasePrice =
                new JTextField();

        txtPurchaseDate =
                new JTextField();

        inputPanel.add(
                new JLabel("Purchase ID")
        );

        inputPanel.add(
                txtPurchaseId
        );

        inputPanel.add(
                new JLabel("Medicine ID")
        );

        inputPanel.add(
                txtMedicineId
        );

        inputPanel.add(
                new JLabel("Supplier Name")
        );

        inputPanel.add(
                txtSupplierName
        );

        inputPanel.add(
                new JLabel("Quantity")
        );

        inputPanel.add(
                txtQuantity
        );

        inputPanel.add(
                new JLabel("Purchase Price")
        );

        inputPanel.add(
                txtPurchasePrice
        );

        inputPanel.add(
                new JLabel("Purchase Date")
        );

        inputPanel.add(
                txtPurchaseDate
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

        JButton btnBack =
                new JButton("← Back");

        buttonPanel.add(btnAdd);

        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnSearch);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnBack);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {

                "Purchase ID",
                "Medicine ID",
                "Supplier Name",
                "Quantity",
                "Purchase Price",
                "Purchase Date"
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
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        btnAdd.addActionListener(
                e -> addPurchase()
        );

        btnUpdate.addActionListener(
                e -> updatePurchase()
        );

        btnDelete.addActionListener(
                e -> deletePurchase()
        );

        btnSearch.addActionListener(
                e -> searchPurchase()
        );

        btnClear.addActionListener(
                e -> clearFields()
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

                            txtPurchaseId.setText(
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
                            txtSupplierName.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString()
                            );

                            txtQuantity.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    3
                                            )
                                            .toString()
                            );

                            txtPurchasePrice.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    4
                                            )
                                            .toString()
                            );

                            txtPurchaseDate.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    5
                                            )
                                            .toString()
                            );
                        }
                    }
                }
        );
    }

    // =====================================================
    // ADD PURCHASE
    // =====================================================

    private void addPurchase() {

        try {

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId.getText()
                    );

            String supplierName =
                    txtSupplierName.getText();

            int quantity =
                    Integer.parseInt(
                            txtQuantity.getText()
                    );

            double purchasePrice =
                    Double.parseDouble(
                            txtPurchasePrice.getText()
                    );

            Date purchaseDate =
                    Date.valueOf(
                            txtPurchaseDate.getText()
                    );

            boolean result =
                    controller.addPurchase(
                            medicineId,
                            supplierName,
                            quantity,
                            purchasePrice,
                            purchaseDate
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Purchase Added Successfully"
                );

                loadPurchases();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Add Purchase Failed"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data.\n"
                    + "Date format: yyyy-MM-dd"
            );
        }
    }

    // =====================================================
    // UPDATE PURCHASE
    // =====================================================

    private void updatePurchase() {

        try {

            int purchaseId =
                    Integer.parseInt(
                            txtPurchaseId.getText()
                    );

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId.getText()
                    );

            String supplierName =
                    txtSupplierName.getText();
            int quantity =
                    Integer.parseInt(
                            txtQuantity.getText()
                    );

            double purchasePrice =
                    Double.parseDouble(
                            txtPurchasePrice.getText()
                    );

            Date purchaseDate =
                    Date.valueOf(
                            txtPurchaseDate.getText()
                    );

            boolean result =
                    controller.updatePurchase(
                            purchaseId,
                            medicineId,
                            supplierName,
                            quantity,
                            purchasePrice,
                            purchaseDate
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Purchase Updated Successfully"
                );

                loadPurchases();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Purchase Failed"
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
    // DELETE PURCHASE
    // =====================================================

    private void deletePurchase() {

        try {

            int purchaseId =
                    Integer.parseInt(
                            txtPurchaseId.getText()
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
                        controller.deletePurchase(
                                purchaseId
                        );

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Purchase Deleted Successfully"
                    );

                    loadPurchases();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete Purchase Failed"
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a purchase first."
            );
        }
    }

    // =====================================================
    // SEARCH PURCHASE
    // =====================================================

    private void searchPurchase() {

        try {

            int purchaseId =
                    Integer.parseInt(
                            txtPurchaseId.getText()
                    );

            Purchase purchase =
                    controller.searchPurchase(
                            purchaseId
                    );

            if (purchase != null) {

                txtMedicineId.setText(
                        String.valueOf(
                                purchase.getMedicineId()
                        )
                );

                txtSupplierName.setText(
                        purchase.getSupplierName()
                );

                txtQuantity.setText(
                        String.valueOf(
                                purchase.getQuantity()
                        )
                );
                txtPurchasePrice.setText(
                        String.valueOf(
                                purchase.getPurchasePrice()
                        )
                );

                txtPurchaseDate.setText(
                        purchase.getPurchaseDate()
                                .toString()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Purchase Not Found"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid Purchase ID."
            );
        }
    }

    // =====================================================
    // LOAD PURCHASES
    // =====================================================

    private void loadPurchases() {

        tableModel.setRowCount(0);

        List<Purchase> list =
                controller.getAllPurchases();

        for (Purchase purchase : list) {

            tableModel.addRow(
                    new Object[] {

                            purchase.getPurchaseId(),

                            purchase.getMedicineId(),

                            purchase.getSupplierName(),

                            purchase.getQuantity(),

                            purchase.getPurchasePrice(),

                            purchase.getPurchaseDate()
                    }
            );
        }
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        txtPurchaseId.setText("");

        txtMedicineId.setText("");

        txtSupplierName.setText("");

        txtQuantity.setText("");

        txtPurchasePrice.setText("");

        txtPurchaseDate.setText("");
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        PurchaseForm form =
                new PurchaseForm();

        form.setVisible(true);
    }
}
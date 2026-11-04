package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

import controller.SaleController;
import model.SaleItem;

public class SaleItemForm extends JFrame {

    private JTextField txtSaleItemId;
    private JTextField txtSaleId;
    private JTextField txtMedicineId;
    private JTextField txtQuantity;
    private JTextField txtUnitPrice;
    private JTextField txtSubtotal;

    private JTable table;
    private DefaultTableModel tableModel;

    private SaleController controller;

    // =========================
    // CONSTRUCTOR
    // =========================
    public SaleItemForm() {

        controller = new SaleController();

        setTitle("Sale Item Management");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
        loadSaleItems();
    }

    // =========================
    // CREATE UI
    // =========================
    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        // =========================
        // INPUT PANEL
        // =========================

        JPanel inputPanel =
                new JPanel(new GridLayout(6, 2, 8, 8));

        txtSaleItemId = new JTextField();
        txtSaleId = new JTextField();
        txtMedicineId = new JTextField();
        txtQuantity = new JTextField();
        txtUnitPrice = new JTextField();
        txtSubtotal = new JTextField();

        // Subtotal ကို user မထည့်ဘဲ
        // Quantity × Unit Price နဲ့တွက်မယ်
        txtSubtotal.setEditable(false);

        inputPanel.add(
                new JLabel("Sale Item ID")
        );
        inputPanel.add(txtSaleItemId);

        inputPanel.add(
                new JLabel("Sale ID")
        );
        inputPanel.add(txtSaleId);

        inputPanel.add(
                new JLabel("Medicine ID")
        );
        inputPanel.add(txtMedicineId);

        inputPanel.add(
                new JLabel("Quantity")
        );
        inputPanel.add(txtQuantity);

        inputPanel.add(
                new JLabel("Unit Price")
        );
        inputPanel.add(txtUnitPrice);

        inputPanel.add(
                new JLabel("Subtotal")
        );
        inputPanel.add(txtSubtotal);

        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        // =========================
        // BUTTON PANEL
        // =========================

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

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnClear);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Sale Item ID",
                "Sale ID",
                "Medicine ID",
                "Quantity",
                "Unit Price",
                "Subtotal"
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

        // =========================
        // BUTTON ACTIONS
        // =========================

        btnAdd.addActionListener(
                e -> addSaleItem()
        );

        btnUpdate.addActionListener(
                e -> updateSaleItem()
        );

        btnDelete.addActionListener(
                e -> deleteSaleItem()
        );

        btnSearch.addActionListener(
                e -> searchSaleItem()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );

        // =========================
        // TABLE CLICK
        // =========================

        table.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int row =
                                table.getSelectedRow();

                        if (row >= 0) {

                            txtSaleItemId.setText(
                                    tableModel
                                    .getValueAt(row, 0)
                                    .toString()
                            );

                            txtSaleId.setText(
                                    tableModel
                                    .getValueAt(row, 1)
                                    .toString()
                            );

                            txtMedicineId.setText(
                                    tableModel
                                    .getValueAt(row, 2)
                                    .toString()
                            );

                            txtQuantity.setText(
                                    tableModel
                                    .getValueAt(row, 3)
                                    .toString()
                            );

                            txtUnitPrice.setText(
                                    tableModel
                                    .getValueAt(row, 4)
                                    .toString()
                            );

                            txtSubtotal.setText(
                                    tableModel
                                    .getValueAt(row, 5)
                                    .toString()
                            );
                        }
                    }
                }
        );
    }

    // =========================
    // CALCULATE SUBTOTAL
    // =========================

    private double calculateSubtotal() {

        int quantity =
                Integer.parseInt(
                        txtQuantity.getText()
                );

        double unitPrice =
                Double.parseDouble(
                        txtUnitPrice.getText()
                );

        double subtotal =
                quantity * unitPrice;

        txtSubtotal.setText(
                String.valueOf(subtotal)
        );

        return subtotal;
    }

    // =========================
    // ADD SALE ITEM
    // =========================

    private void addSaleItem() {

        try {

            int saleId =
                    Integer.parseInt(
                            txtSaleId.getText()
                    );

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId.getText()
                    );

            int quantity =
                    Integer.parseInt(
                            txtQuantity.getText()
                    );

            double unitPrice =
                    Double.parseDouble(
                            txtUnitPrice.getText()
                    );

            double subtotal =
                    calculateSubtotal();
            boolean result =
                    controller.addSaleItem(
                            saleId,
                            medicineId,
                            quantity,
                            unitPrice,
                            subtotal
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Item Added Successfully"
                );

                loadSaleItems();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Add Sale Item Failed"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data."
            );
        }
    }

    // =========================
    // UPDATE SALE ITEM
    // =========================

    private void updateSaleItem() {

        try {

            int saleItemId =
                    Integer.parseInt(
                            txtSaleItemId.getText()
                    );

            int saleId =
                    Integer.parseInt(
                            txtSaleId.getText()
                    );

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId.getText()
                    );

            int quantity =
                    Integer.parseInt(
                            txtQuantity.getText()
                    );

            double unitPrice =
                    Double.parseDouble(
                            txtUnitPrice.getText()
                    );

            double subtotal =
                    calculateSubtotal();

            boolean result =
                    controller.updateSaleItem(
                            saleItemId,
                            saleId,
                            medicineId,
                            quantity,
                            unitPrice,
                            subtotal
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Item Updated Successfully"
                );

                loadSaleItems();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Failed"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data."
            );
        }
    }

    // =========================
    // DELETE SALE ITEM
    // =========================

    private void deleteSaleItem() {

        try {

            int saleItemId =
                    Integer.parseInt(
                            txtSaleItemId.getText()
                    );

            int answer =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (answer ==
                    JOptionPane.YES_OPTION) {

                boolean result =
                        controller.deleteSaleItem(
                                saleItemId
                        );

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Sale Item Deleted Successfully"
                    );

                    loadSaleItems();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete Failed"
                    );
                }
            }

        } catch (Exception e) {
        	JOptionPane.showMessageDialog(
                    this,
                    "Select a Sale Item first."
            );
        }
    }

    // =========================
    // SEARCH SALE ITEM
    // =========================

    private void searchSaleItem() {

        try {

            int saleItemId =
                    Integer.parseInt(
                            txtSaleItemId.getText()
                    );

            SaleItem item =
                    controller.searchSaleItem(
                            saleItemId
                    );

            if (item != null) {

                txtSaleId.setText(
                        String.valueOf(
                                item.getSaleId()
                        )
                );

                txtMedicineId.setText(
                        String.valueOf(
                                item.getMedicineId()
                        )
                );

                txtQuantity.setText(
                        String.valueOf(
                                item.getQuantity()
                        )
                );

                txtUnitPrice.setText(
                        String.valueOf(
                                item.getUnitPrice()
                        )
                );

                txtSubtotal.setText(
                        String.valueOf(
                                item.getSubtotal()
                        )
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Item Not Found"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid Sale Item ID."
            );
        }
    }

    // =========================
    // LOAD SALE ITEMS
    // =========================

    private void loadSaleItems() {

        tableModel.setRowCount(0);

        List<SaleItem> list =
                controller.getAllSaleItems();

        for (SaleItem item : list) {

            tableModel.addRow(
                    new Object[] {

                            item.getSaleItemId(),

                            item.getSaleId(),

                            item.getMedicineId(),

                            item.getQuantity(),

                            item.getUnitPrice(),

                            item.getSubtotal()
                    }
            );
        }
    }

    // =========================
    // CLEAR
    // =========================

    private void clearFields() {

        txtSaleItemId.setText("");
        txtSaleId.setText("");
        txtMedicineId.setText("");
        txtQuantity.setText("");
        txtUnitPrice.setText("");
        txtSubtotal.setText("");
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SaleItemForm form =
                new SaleItemForm();

        form.setVisible(true);
    }
}
        
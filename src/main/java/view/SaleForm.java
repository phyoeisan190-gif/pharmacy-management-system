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

import controller.SaleController;
import model.Sale;

public class SaleForm extends JFrame {

    private JTextField txtSaleId;
    private JTextField txtSaleDate;
    private JTextField txtTotalAmount;

    private JTable table;
    private DefaultTableModel tableModel;

    private SaleController controller;

    // =========================
    // CONSTRUCTOR
    // =========================
    public SaleForm() {

        controller = new SaleController();

        setTitle("Sale Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
        loadSales();
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
                new JPanel(new GridLayout(3, 2, 10, 10));

        txtSaleId = new JTextField();
        txtSaleDate = new JTextField();
        txtTotalAmount = new JTextField();

        // Sale Date ကို database က auto ထည့်မယ်
        txtSaleDate.setEditable(false);

        inputPanel.add(
                new JLabel("Sale ID")
        );
        inputPanel.add(txtSaleId);

        inputPanel.add(
                new JLabel("Sale Date")
        );
        inputPanel.add(txtSaleDate);

        inputPanel.add(
                new JLabel("Total Amount")
        );
        inputPanel.add(txtTotalAmount);

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
                "Sale ID",
                "Sale Date",
                "Total Amount"
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
                e -> addSale()
        );

        btnUpdate.addActionListener(
                e -> updateSale()
        );

        btnDelete.addActionListener(
                e -> deleteSale()
        );

        btnSearch.addActionListener(
                e -> searchSale()
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

                            txtSaleId.setText(
                                    tableModel
                                    .getValueAt(row, 0)
                                    .toString()
                            );

                            txtSaleDate.setText(
                                    tableModel
                                    .getValueAt(row, 1)
                                    .toString()
                            );

                            txtTotalAmount.setText(
                                    tableModel
                                    .getValueAt(row, 2)
                                    .toString()
                            );
                        }
                    }
                }
        );
    }

    // =========================
    // ADD SALE
    // =========================

    private void addSale() {

        try {

            double totalAmount =
                    Double.parseDouble(
                            txtTotalAmount.getText()
                    );

            boolean result =
                    controller.addSale(
                            totalAmount
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Added Successfully"
                );

                loadSales();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Add Sale Failed"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid Total Amount."
            );
        }
    }

    // =========================
    // UPDATE SALE
    // =========================

    private void updateSale() {

        try {

            int saleId =
                    Integer.parseInt(
                            txtSaleId.getText()
                    );

            double totalAmount =
                    Double.parseDouble(
                            txtTotalAmount.getText()
                    );

            boolean result =
                    controller.updateSale(
                            saleId,
                            totalAmount
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Updated Successfully"
                );

                loadSales();
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
    // DELETE SALE
    // =========================

    private void deleteSale() {

        try {

            int saleId =
                    Integer.parseInt(
                            txtSaleId.getText()
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
                        controller.deleteSale(
                                saleId
                        );

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Sale Deleted Successfully"
                    );

                    loadSales();
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
                    "Select a Sale first."
            );
        }
    }

    // =========================
    // SEARCH SALE
    // =========================

    private void searchSale() {

        try {

            int saleId =
                    Integer.parseInt(
                            txtSaleId.getText()
                    );

            Sale sale =
                    controller.searchSale(
                            saleId
                    );

            if (sale != null) {

                Timestamp saleDate =
                        sale.getSaleDate();

                if (saleDate != null) {

                    txtSaleDate.setText(
                            saleDate.toString()
                    );
                }

                txtTotalAmount.setText(
                        String.valueOf(
                                sale.getTotalAmount()
                        )
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Not Found"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid Sale ID."
            );
        }
    }

    // =========================
    // LOAD SALES
    // =========================

    private void loadSales() {

        tableModel.setRowCount(0);

        List<Sale> list =
                controller.getAllSales();

        for (Sale sale : list) {

            tableModel.addRow(
                    new Object[] {

                            sale.getSaleId(),

                            sale.getSaleDate(),

                            sale.getTotalAmount()
                    }
            );
        }
    }

    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        txtSaleId.setText("");
        txtSaleDate.setText("");
        txtTotalAmount.setText("");
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SaleForm form =
                new SaleForm();

        form.setVisible(true);
    }
}
            
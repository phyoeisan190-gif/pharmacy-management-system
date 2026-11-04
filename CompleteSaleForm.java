package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class CompleteSaleForm extends JFrame {

    private JTextField txtMedicineId;
    private JTextField txtQuantity;
    private JTextField txtUnitPrice;
    private JTextField txtSubtotal;
    private JTextField txtTotalAmount;

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnCalculate;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnSaveSale;
    private JButton btnGenerateBill;
    private JButton btnClear;
    private JButton btnBack;

    private SaleController controller;

    private List<SaleItemData> items;

    // Dashboard
    private MainForm mainForm;

    // =====================================================
    // DEFAULT CONSTRUCTOR
    // =====================================================

    public CompleteSaleForm() {

        controller = new SaleController();

        items = new ArrayList<>();

        setTitle(
                "Pharmacy Management System - Sale"
        );

        setSize(1000, 650);

        setLocationRelativeTo(null);

        // Application မပိတ်အောင်
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();
    }

    // =====================================================
    // CONSTRUCTOR FROM DASHBOARD
    // =====================================================

    public CompleteSaleForm(MainForm mainForm) {

        this.mainForm = mainForm;

        controller = new SaleController();

        items = new ArrayList<>();

        setTitle(
                "Pharmacy Management System - Complete Sale"
        );

        setSize(1000, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        // =================================================
        // INPUT PANEL
        // =================================================

        JPanel inputPanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                8,
                                8
                        )
                );

        txtMedicineId =
                new JTextField();

        txtQuantity =
                new JTextField();

        txtUnitPrice =
                new JTextField();

        txtSubtotal =
                new JTextField();

        txtTotalAmount =
                new JTextField();

        txtSubtotal.setEditable(false);

        txtTotalAmount.setEditable(false);

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
                new JLabel("Unit Price")
        );

        inputPanel.add(
                txtUnitPrice
        );

        inputPanel.add(
                new JLabel("Subtotal")
        );
        inputPanel.add(
                txtSubtotal
        );

        inputPanel.add(
                new JLabel("Total Amount")
        );

        inputPanel.add(
                txtTotalAmount
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

        btnCalculate =
                new JButton("Calculate");

        btnAddItem =
                new JButton("Add Item");

        btnRemoveItem =
                new JButton("Remove Item");

        btnSaveSale =
                new JButton("Save Sale");

        btnGenerateBill =
                new JButton("Generate Bill");

        btnClear =
                new JButton("Clear");

        btnBack =
                new JButton("← Back");

        Font buttonFont =
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                );

        btnCalculate.setFont(buttonFont);

        btnAddItem.setFont(buttonFont);

        btnRemoveItem.setFont(buttonFont);

        btnSaveSale.setFont(buttonFont);

        btnGenerateBill.setFont(buttonFont);

        btnClear.setFont(buttonFont);

        btnBack.setFont(buttonFont);

        buttonPanel.add(
                btnCalculate
        );

        buttonPanel.add(
                btnAddItem
        );

        buttonPanel.add(
                btnRemoveItem
        );

        buttonPanel.add(
                btnSaveSale
        );

        buttonPanel.add(
                btnGenerateBill
        );

        buttonPanel.add(
                btnClear
        );

        buttonPanel.add(
                btnBack
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {

                "Medicine ID",
                "Quantity",
                "Unit Price",
                "Subtotal"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        table =
                new JTable(
                        tableModel
                );

        table.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        mainPanel.add(
                scrollPane,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        btnCalculate.addActionListener(
                e -> calculateSubtotal()
        );

        btnAddItem.addActionListener(
                e -> addItem()
        );

        btnRemoveItem.addActionListener(
                e -> removeItem()
        );

        btnSaveSale.addActionListener(
                e -> saveSale()
        );

        btnGenerateBill.addActionListener(
                e -> generateBill()
        );

        btnClear.addActionListener(
                e -> clearAll()
        );

        // =================================================
        // BACK TO DASHBOARD
        // =================================================

        btnBack.addActionListener(e -> {

            // Order/Sale page ပိတ်
            dispose();

            // Dashboard ပြန်ဖွင့်
            if (mainForm != null) {
            	mainForm.setLocationRelativeTo(null);

                mainForm.setVisible(true);
            }
        });
    }

    // =====================================================
    // CALCULATE SUBTOTAL
    // =====================================================

    private void calculateSubtotal() {

        try {

            int quantity =
                    Integer.parseInt(
                            txtQuantity
                                    .getText()
                                    .trim()
                    );

            double unitPrice =
                    Double.parseDouble(
                            txtUnitPrice
                                    .getText()
                                    .trim()
                    );

            if (quantity <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity must be greater than 0."
                );

                return;
            }

            if (unitPrice < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unit Price cannot be negative."
                );

                return;
            }

            double subtotal =
                    quantity * unitPrice;

            txtSubtotal.setText(
                    String.format(
                            "%.2f",
                            subtotal
                    )
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid Quantity and Unit Price."
            );
        }
    }

    // =====================================================
    // ADD ITEM
    // =====================================================

    private void addItem() {

        try {

            int medicineId =
                    Integer.parseInt(
                            txtMedicineId
                                    .getText()
                                    .trim()
                    );

            int quantity =
                    Integer.parseInt(
                            txtQuantity
                                    .getText()
                                    .trim()
                    );

            double unitPrice =
                    Double.parseDouble(
                            txtUnitPrice
                                    .getText()
                                    .trim()
                    );

            if (medicineId <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid Medicine ID."
                );

                return;
            }

            if (quantity <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity must be greater than 0."
                );

                return;
            }

            if (unitPrice < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unit Price cannot be negative."
                );

                return;
            }

            double subtotal =
                    quantity * unitPrice;

            txtSubtotal.setText(
                    String.format(
                            "%.2f",
                            subtotal
                    )
            );

            SaleItemData item =
                    new SaleItemData(
                            medicineId,
                            quantity,
                            unitPrice,
                            subtotal
                    );

            items.add(item);

            tableModel.addRow(
                    new Object[] {

                            medicineId,

                            quantity,

                            String.format(
                                    "%.2f",
                                    unitPrice
                            ),
                            String.format(
                                    "%.2f",
                                    subtotal
                            )
                    }
            );

            updateTotal();

            txtMedicineId.setText("");

            txtQuantity.setText("");

            txtUnitPrice.setText("");

            txtSubtotal.setText("");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid data."
            );
        }
    }

    // =====================================================
    // REMOVE ITEM
    // =====================================================

    private void removeItem() {

        int row =
                table.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an item first."
            );

            return;
        }

        items.remove(row);

        tableModel.removeRow(row);

        updateTotal();
    }

    // =====================================================
    // UPDATE TOTAL
    // =====================================================

    private void updateTotal() {

        double total = 0;

        for (
                SaleItemData item :
                items
        ) {

            total += item.subtotal;
        }

        txtTotalAmount.setText(
                String.format(
                        "%.2f",
                        total
                )
        );
    }

    // =====================================================
    // SAVE SALE
    // =====================================================

    private void saveSale() {

        if (items.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one medicine."
            );

            return;
        }

        try {

            double totalAmount = 0;

            for (
                    SaleItemData item :
                    items
            ) {

                totalAmount +=
                        item.subtotal;
            }

            boolean saleResult =
                    controller.addSale(
                            totalAmount
                    );

            if (!saleResult) {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to create Sale."
                );

                return;
            }

            List<model.Sale> sales =
                    controller.getAllSales();

            if (sales.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale created but Sale ID could not be found."
                );

                return;
            }

            model.Sale lastSale =
                    sales.get(
                            sales.size() - 1
                    );

            int saleId =
                    lastSale.getSaleId();

            boolean allItemsAdded = true;

            for (
                    SaleItemData item :
                    items
            ) {

                boolean itemResult =
                        controller.addSaleItem(
                                saleId,
                                item.medicineId,
                                item.quantity,
                                item.unitPrice,
                                item.subtotal
                        );

                if (!itemResult) {

                    allItemsAdded = false;

                    break;
                }
            }

            if (allItemsAdded) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale Completed Successfully!\n\n"
                                + "Sale ID: "
                                + saleId
                                + "\nTotal Amount: "
                                + String.format(
                                        "%.2f",
                                        totalAmount
                                )
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Sale created, but one or more Sale Items failed."
                        );
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    JOptionPane.showMessageDialog(
                            this,
                            "Error while saving sale."
                    );
                }
            }

            // =====================================================
            // GENERATE BILL
            // =====================================================

           

            // =====================================================
            // CLEAR ALL
            // =====================================================

            private void clearAll() {

                txtMedicineId.setText("");

                txtQuantity.setText("");

                txtUnitPrice.setText("");

                txtSubtotal.setText("");

                txtTotalAmount.setText("");

                items.clear();

                tableModel.setRowCount(0);
            }

            // =====================================================
            // SALE ITEM DATA
            // =====================================================

            private static class SaleItemData {

                int medicineId;

                int quantity;

                double unitPrice;

                double subtotal;

                SaleItemData(
                        int medicineId,
                        int quantity,
                        double unitPrice,
                        double subtotal
                ) {

                    this.medicineId =
                            medicineId;

                    this.quantity =
                            quantity;

                    this.unitPrice =
                            unitPrice;

                    this.subtotal =
                            subtotal;
                }
            }

            // =====================================================
            // MAIN
            // =====================================================

            public static void main(
                    String[] args
            ) {

                CompleteSaleForm form =
                        new CompleteSaleForm();

                form.setVisible(true);
           
            }
         // =====================================================
         // GENERATE BILL - SCREEN RECEIPT
         // =====================================================
         private void generateBill() {

             if (items.isEmpty()) {
                 JOptionPane.showMessageDialog(
                         this,
                         "Please add at least one medicine first.",
                         "Generate Bill",
                         JOptionPane.WARNING_MESSAGE
                 );
                 return;
             }

             double total = 0;

             StringBuilder bill = new StringBuilder();

             bill.append("================================================\n");
             bill.append("              PHARMACY MANAGEMENT               \n");
             bill.append("                    RECEIPT                     \n");
             bill.append("================================================\n");
             bill.append("Date : ")
                 .append(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                 .format(new java.util.Date()))
                 .append("\n");

             bill.append("------------------------------------------------\n");
             bill.append(String.format(
                     "%-10s %-10s %-12s %-12s%n",
                     "Med ID",
                     "Qty",
                     "Unit Price",
                     "Subtotal"
             ));
             bill.append("------------------------------------------------\n");

             for (SaleItemData item : items) {

                 total += item.subtotal;

                 bill.append(String.format(
                         "%-10d %-10d %-12.2f %-12.2f%n",
                         item.medicineId,
                         item.quantity,
                         item.unitPrice,
                         item.subtotal
                 ));
             }

             bill.append("------------------------------------------------\n");

             bill.append(String.format(
                     "%-28s %12.2f%n",
                     "TOTAL AMOUNT:",
                     total
             ));

             bill.append("================================================\n");
             bill.append("              THANK YOU VERY MUCH               \n");
             bill.append("================================================\n");


             // =================================================
             // RECEIPT WINDOW
             // =================================================

             JFrame receiptFrame = new JFrame("Pharmacy - Bill / Receipt");

             receiptFrame.setSize(600, 700);
             receiptFrame.setLocationRelativeTo(this);
             receiptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

             JPanel receiptPanel = new JPanel(new BorderLayout(10, 10));

             // =================================================
             // RECEIPT TEXT
             // =================================================

             javax.swing.JTextArea receiptArea =
                     new javax.swing.JTextArea();

             receiptArea.setText(bill.toString());

             receiptArea.setFont(
                     new Font("Monospaced", Font.PLAIN, 14)
             );

             receiptArea.setEditable(false);

             receiptArea.setLineWrap(false);

             receiptArea.setCaretPosition(0);


             // =================================================
             // SCROLL
             // =================================================

             JScrollPane receiptScroll =
                     new JScrollPane(receiptArea);

             receiptPanel.add(
                     receiptScroll,
                     BorderLayout.CENTER
             );


             // =================================================
             // PRINT BUTTON
             // =================================================

             JPanel printPanel = new JPanel();

             JButton btnPrintBill =
                     new JButton("Print Bill");

             JButton btnClose =
                     new JButton("Close");

             Font receiptButtonFont =
                     new Font("Arial", Font.BOLD, 14);

             btnPrintBill.setFont(receiptButtonFont);
             btnClose.setFont(receiptButtonFont);

             printPanel.add(btnPrintBill);
             printPanel.add(btnClose);

             receiptPanel.add(
                     printPanel,
                     BorderLayout.SOUTH
             );


             // =================================================
             // PRINT ACTION
             // =================================================

             btnPrintBill.addActionListener(e -> {

                 try {

                     boolean complete =
                             receiptArea.print();

                     if (complete) {
                    	 JOptionPane.showMessageDialog(
                                 receiptFrame,
                                 "Bill printed successfully!",
                                 "Print Bill",
                                 JOptionPane.INFORMATION_MESSAGE
                         );

                     } else {

                         JOptionPane.showMessageDialog(
                                 receiptFrame,
                                 "Printing was cancelled.",
                                 "Print Bill",
                                 JOptionPane.WARNING_MESSAGE
                         );
                     }

                 } catch (Exception ex) {

                     JOptionPane.showMessageDialog(
                             receiptFrame,
                             "Printing failed!\n"
                             + ex.getMessage(),
                             "Print Error",
                             JOptionPane.ERROR_MESSAGE
                     );
                 }
             });


             // =================================================
             // CLOSE RECEIPT
             // =================================================

             btnClose.addActionListener(e ->
                     receiptFrame.dispose()
             );


             receiptFrame.add(receiptPanel);

             receiptFrame.setVisible(true);
         }
                     }
        
            
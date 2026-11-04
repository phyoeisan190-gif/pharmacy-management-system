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

import controller.MedicineController;
import model.Medicine;

public class MedicineForm extends JFrame {

    private JTextField txtId;
    private JTextField txtPharmacyId;
    private JTextField txtName;
    private JTextField txtType;
    private JTextField txtCompany;
    private JTextField txtMfgDate;
    private JTextField txtExpDate;
    private JTextField txtPurchasePrice;
    private JTextField txtSellingPrice;

    private JTable table;
    private DefaultTableModel tableModel;

    private MedicineController controller;

    // =====================================================
    // MAIN DASHBOARD
    // =====================================================

    private MainForm mainForm;

    // =====================================================
    // OLD CONSTRUCTOR
    // =====================================================

    public MedicineForm() {

        controller = new MedicineController();

        setTitle("Medicine Management");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadMedicines();
    }

    // =====================================================
    // NEW CONSTRUCTOR
    // Dashboard ကနေ Medicine ကိုဖွင့်တဲ့အခါသုံးမယ်
    // =====================================================

    public MedicineForm(MainForm mainForm) {

        this.mainForm = mainForm;

        controller = new MedicineController();

        setTitle("Medicine Management");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadMedicines();
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
                                5,
                                4,
                                5,
                                5
                        )
                );

        txtId = new JTextField();
        txtPharmacyId = new JTextField();
        txtName = new JTextField();
        txtType = new JTextField();
        txtCompany = new JTextField();
        txtMfgDate = new JTextField();
        txtExpDate = new JTextField();
        txtPurchasePrice = new JTextField();
        txtSellingPrice = new JTextField();

        inputPanel.add(
                new JLabel("Medicine ID")
        );
        inputPanel.add(txtId);

        inputPanel.add(
                new JLabel("Pharmacy ID")
        );
        inputPanel.add(txtPharmacyId);

        inputPanel.add(
                new JLabel("Medicine Name")
        );
        inputPanel.add(txtName);

        inputPanel.add(
                new JLabel("Medicine Type")
        );
        inputPanel.add(txtType);

        inputPanel.add(
                new JLabel("Company")
        );
        inputPanel.add(txtCompany);

        inputPanel.add(
                new JLabel("MFG Date")
        );
        inputPanel.add(txtMfgDate);

        inputPanel.add(
                new JLabel("EXP Date")
        );
        inputPanel.add(txtExpDate);
        inputPanel.add(
                new JLabel("Purchase Price")
        );
        inputPanel.add(txtPurchasePrice);

        inputPanel.add(
                new JLabel("Selling Price")
        );
        inputPanel.add(txtSellingPrice);

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

        // =================================================
        // BACK BUTTON
        // =================================================

        JButton btnBack =
                new JButton("← Back");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnClear);

        // Back ကိုပါထည့်
        buttonPanel.add(btnBack);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {

                "ID",
                "Pharmacy ID",
                "Name",
                "Type",
                "Company",
                "MFG Date",
                "EXP Date",
                "Purchase Price",
                "Selling Price"
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
                e -> addMedicine()
        );

        btnUpdate.addActionListener(
                e -> updateMedicine()
        );

        btnDelete.addActionListener(
                e -> deleteMedicine()
        );

        btnSearch.addActionListener(
                e -> searchMedicine()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );

        // =================================================
        // BACK TO DASHBOARD
        // =================================================

        btnBack.addActionListener(e -> {

            // Medicine window ပိတ်
            dispose();

            // Dashboard ပြန်ဖွင့်
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
                        	txtId.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );

                            txtPharmacyId.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    1
                                            )
                                            .toString()
                            );

                            txtName.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString()
                            );

                            txtType.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    3
                                            )
                                            .toString()
                            );

                            txtCompany.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    4
                                            )
                                            .toString()
                            );

                            txtMfgDate.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    5
                                            )
                                            .toString()
                            );

                            txtExpDate.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    6
                                            )
                                            .toString()
                            );

                            txtPurchasePrice.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    7
                                            )
                                            .toString()
                            );

                            txtSellingPrice.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    8
                                            )
                                            .toString()
                            );
                        }
                    }
                }
        );
    }

    // =====================================================
    // ADD MEDICINE
    // =====================================================

    private void addMedicine() {

        try {

            int pharmacyId =
                    Integer.parseInt(
                            txtPharmacyId
                                    .getText()
                    );
            String name =
                    txtName.getText();

            String type =
                    txtType.getText();

            String company =
                    txtCompany.getText();

            Date mfgDate =
                    Date.valueOf(
                            txtMfgDate
                                    .getText()
                    );

            Date expDate =
                    Date.valueOf(
                            txtExpDate
                                    .getText()
                    );

            double purchasePrice =
                    Double.parseDouble(
                            txtPurchasePrice
                                    .getText()
                    );

            double sellingPrice =
                    Double.parseDouble(
                            txtSellingPrice
                                    .getText()
                    );

            boolean result =
                    controller.addMedicine(
                            pharmacyId,
                            name,
                            type,
                            company,
                            mfgDate,
                            expDate,
                            purchasePrice,
                            sellingPrice
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Added Successfully"
                );

                loadMedicines();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Add Failed"
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
    // UPDATE MEDICINE
    // =====================================================

    private void updateMedicine() {

        try {

            int id =
                    Integer.parseInt(
                            txtId.getText()
                    );

            int pharmacyId =
                    Integer.parseInt(
                            txtPharmacyId
                                    .getText()
                    );

            String name =
                    txtName.getText();

            String type =
                    txtType.getText();

            String company =
                    txtCompany.getText();

            Date mfgDate =
                    Date.valueOf(
                            txtMfgDate
                                    .getText()
                    );

            Date expDate =
                    Date.valueOf(
                            txtExpDate
                                    .getText()
                    );

            double purchasePrice =
                    Double.parseDouble(
                            txtPurchasePrice
                                    .getText()
                    );

            double sellingPrice =
                    Double.parseDouble(
                            txtSellingPrice
                                    .getText()
                    );

            boolean result =
                    controller.updateMedicine(
                            id,
                            pharmacyId,
                            name,
                            type,
                            company,
                            mfgDate,
                            expDate,
                            purchasePrice,
                            sellingPrice
                    );

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Updated Successfully"
                );

                loadMedicines();

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

    // =====================================================
    // DELETE MEDICINE
    // =====================================================

    private void deleteMedicine() {

        try {

            int id =
                    Integer.parseInt(
                            txtId.getText()
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
                        controller.deleteMedicine(id);

                if (result) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Medicine Deleted Successfully"
                    );

                    loadMedicines();

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
                    "Select a medicine first."
            );
        }
    }

    // =====================================================
    // SEARCH MEDICINE
    // =====================================================

    private void searchMedicine() {

        try {

            int id =
                    Integer.parseInt(
                            txtId.getText()
                    );

            Medicine medicine =
                    controller.searchMedicine(id);

            if (medicine != null) {

                txtPharmacyId.setText(
                        String.valueOf(
                                medicine
                                        .getPharmacyId()
                        )
                );

                txtName.setText(
                        medicine
                                .getMedicineName()
                );

                txtType.setText(
                        medicine
                                .getMedicineType()
                );

                txtCompany.setText(
                        medicine
                                .getCompany()
                );

                txtMfgDate.setText(
                        medicine
                                .getManufacturingDate()
                                .toString()
                );

                txtExpDate.setText(
                        medicine
                                .getExpiryDate()
                                .toString()
                );

                txtPurchasePrice.setText(
                        String.valueOf(
                                medicine
                                        .getPurchasePrice()
                        )
                );

                txtSellingPrice.setText(
                        String.valueOf(
                                medicine
                                        .getSellingPrice()
                        )
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Not Found"
                );
            }

        } catch (Exception e) {
        	JOptionPane.showMessageDialog(
                    this,
                    "Enter valid Medicine ID."
            );
        }
    }

    // =====================================================
    // LOAD TABLE
    // =====================================================

    private void loadMedicines() {

        tableModel.setRowCount(0);

        List<Medicine> list =
                controller.getAllMedicines();

        for (Medicine medicine : list) {

            tableModel.addRow(
                    new Object[] {

                            medicine
                                    .getMedicineId(),

                            medicine
                                    .getPharmacyId(),

                            medicine
                                    .getMedicineName(),

                            medicine
                                    .getMedicineType(),

                            medicine
                                    .getCompany(),

                            medicine
                                    .getManufacturingDate(),

                            medicine
                                    .getExpiryDate(),

                            medicine
                                    .getPurchasePrice(),

                            medicine
                                    .getSellingPrice()
                    }
            );
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        txtId.setText("");

        txtPharmacyId.setText("");

        txtName.setText("");

        txtType.setText("");

        txtCompany.setText("");

        txtMfgDate.setText("");

        txtExpDate.setText("");

        txtPurchasePrice.setText("");

        txtSellingPrice.setText("");
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        MedicineForm form =
                new MedicineForm();

        form.setVisible(true);
    }

        }
                        
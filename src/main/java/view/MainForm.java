package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainForm extends JFrame {

    private String role;

    // ==========================================
    // DEFAULT CONSTRUCTOR
    // ==========================================

    public MainForm() {
        this("staff");
    }

    // ==========================================
    // ROLE CONSTRUCTOR
    // ==========================================

    public MainForm(String role) {

        this.role = role;

        setTitle("Pharmacy Management System");
        setSize(1100, 700);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    // ==========================================
    // CREATE UI
    // ==========================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        // ======================================
        // HEADER
        // ======================================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                new Color(40, 100, 160)
        );

        JLabel title =
                new JLabel(
                        "PHARMACY MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(Color.WHITE);

        JLabel user =
                new JLabel(
                        "Logged in as: "
                        + role.toUpperCase(),
                        SwingConstants.CENTER
                );

        user.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        user.setForeground(Color.WHITE);

        header.add(
                title,
                BorderLayout.CENTER
        );

        header.add(
                user,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // ======================================
        // MENU
        // ======================================

        JPanel menu =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                10,
                                10
                        )
                );

        menu.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // ======================================
        // DASHBOARD
        // ======================================

        JButton btnDashboard =
                new JButton(
                        "Dashboard"
                );

        menu.add(btnDashboard);

        btnDashboard.addActionListener(
                e -> showDashboard()
        );

        // ======================================
        // MEDICINE
        // ADMIN ONLY
        // ======================================

        JButton btnMedicine =
                new JButton(
                        "Medicine Management"
                );

        menu.add(btnMedicine);

        btnMedicine.addActionListener(
                e -> openMedicine()
        );

        // ======================================
        // SALE
        // STAFF ONLY
        // ======================================
        JButton btnSale =
                new JButton(
                        "Sale"
                );

        menu.add(btnSale);

        btnSale.addActionListener(
                e -> openSale()
        );

        // ======================================
        // PURCHASE
        // STAFF ONLY
        // ======================================

        JButton btnPurchase =
                new JButton(
                        "Purchase"
                );

        menu.add(btnPurchase);

        btnPurchase.addActionListener(
                e -> openPurchase()
        );

        // ======================================
        // STOCK
        // STAFF ONLY
        // ======================================

        JButton btnStock =
                new JButton(
                        "Stock"
                );

        menu.add(btnStock);

        btnStock.addActionListener(
                e -> openStock()
        );

        // ======================================
        // ORDER
        // STAFF ONLY
        // ======================================

        JButton btnOrder =
                new JButton(
                        "Orders"
                );

        menu.add(btnOrder);

        btnOrder.addActionListener(
                e -> openOrder()
        );

        // ======================================
        // LOGOUT
        // ======================================

        JButton btnLogout =
                new JButton(
                        "Logout"
                );

        menu.add(btnLogout);

        btnLogout.addActionListener(
                e -> logout()
        );

        // ======================================
        // ROLE PERMISSION
        // ======================================

        if (
                role != null
                && role.equalsIgnoreCase("admin")
        ) {

            // ADMIN

            btnMedicine.setVisible(true);

            btnSale.setVisible(false);

            btnPurchase.setVisible(false);

            btnStock.setVisible(false);

            btnOrder.setVisible(false);

        } else {

            // STAFF

            btnMedicine.setVisible(false);

            btnSale.setVisible(true);

            btnPurchase.setVisible(true);

            btnStock.setVisible(true);

            btnOrder.setVisible(true);
        }

        mainPanel.add(
                menu,
                BorderLayout.WEST
        );

        // ======================================
        // DASHBOARD AREA
        // ======================================

        JPanel dashboard =
                new JPanel(
                        new BorderLayout()
                );

        dashboard.setBackground(
                Color.WHITE
        );

        JLabel welcome =
                new JLabel(
                        "Welcome to Main Dashboard",
                        SwingConstants.CENTER
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        welcome.setForeground(
                new Color(
                        40,
                        100,
                        160
                )
        );

        dashboard.add(
                welcome,
                BorderLayout.CENTER
        );

        mainPanel.add(
                dashboard,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // ==========================================
    // SHOW DASHBOARD
    // ==========================================

    private void showDashboard() {

        JOptionPane.showMessageDialog(
                this,
                "You are already on Main Dashboard."
        );
    }

    // ==========================================
    // OPEN MEDICINE
    // ==========================================

    private void openMedicine() {

        try {

            MedicineForm form =
                    new MedicineForm(this);

            form.setVisible(true);
            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cannot open Medicine Form.\n"
                    + e.getMessage()
            );
        }
    }

    // ==========================================
    // OPEN SALE
    // ==========================================

    private void openSale() {

        try {

            CompleteSaleForm form =
                    new CompleteSaleForm(this);

            form.setVisible(true);

            setVisible(false);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Cannot open Sale Form.\n"
                    + e.getMessage()
            );
        }
    }

    // ==========================================
    // OPEN PURCHASE
    // ==========================================

    private void openPurchase() {

        try {

            PurchaseForm form =
                    new PurchaseForm(this);

            form.setVisible(true);

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cannot open Purchase Form.\n"
                    + e.getMessage()
            );
        }
    }

    // ==========================================
    // OPEN STOCK
    // ==========================================

    private void openStock() {

        try {

            StockForm form =
                    new StockForm(this);

            form.setVisible(true);

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cannot open Stock Form.\n"
                    + e.getMessage()
            );
        }
    }

    // ==========================================
    // OPEN ORDER
    // ==========================================

 // =========================
 // OPEN SALE FORM
 // =========================
    private void openOrder() {

        try {

            CompleteSaleForm form =
                    new CompleteSaleForm(this);

            form.setLocationRelativeTo(null);

            form.setVisible(true);

            
            setVisible(false);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Cannot open Order Form.\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==========================================
    // LOGOUT
    // ==========================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result
                == JOptionPane.YES_OPTION
        ) {

            dispose();

            try {

                LoginForm login =
                        new LoginForm();

                login.setVisible(true);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Cannot open Login Form."
                );
            }
        }
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(
            String[] args
    ) {

        MainForm form =
                new MainForm("staff");

        form.setVisible(true);
    }
}

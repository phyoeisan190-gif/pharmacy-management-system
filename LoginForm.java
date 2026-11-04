package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginController;
import model.User;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private LoginController controller;

    // =========================
    // CONSTRUCTOR
    // =========================

    public LoginForm() {

        controller = new LoginController();

        setTitle("Pharmacy Management System - Login");

        setSize(450, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createUI();
    }

    // =========================
    // CREATE UI
    // =========================

    private void createUI() {

        JPanel panel =
                new JPanel(
                        new GridLayout(4, 2, 10, 10)
                );

        JLabel lblTitle =
                new JLabel(
                        "PHARMACY LOGIN",
                        JLabel.CENTER
                );

        JLabel lblUsername =
                new JLabel("Username");

        JLabel lblPassword =
                new JLabel("Password");

        txtUsername =
                new JTextField();

        txtPassword =
                new JPasswordField();

        JButton btnLogin =
                new JButton("Login");

        JButton btnClear =
                new JButton("Clear");

        panel.add(lblTitle);
        panel.add(new JLabel(""));

        panel.add(lblUsername);
        panel.add(txtUsername);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(btnLogin);
        panel.add(btnClear);

        add(panel);

        // =========================
        // LOGIN BUTTON
        // =========================

        btnLogin.addActionListener(
                e -> login()
        );

        // =========================
        // CLEAR BUTTON
        // =========================

        btnClear.addActionListener(e -> {

            txtUsername.setText("");

            txtPassword.setText("");

            txtUsername.requestFocus();
        });
    }

    // =========================
    // LOGIN
    // =========================

    private void login() {

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                );

        // =========================
        // EMPTY CHECK
        // =========================

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }

        // =========================
        // CHECK DATABASE
        // =========================

        User user =
                controller.login(
                        username,
                        password
                );

        // =========================
        // INVALID LOGIN
        // =========================

        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPassword.setText("");

            return;
        }

        // =========================
        // ADMIN LOGIN
        // =========================

        if (controller.isAdmin(user)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin Login Successful!"
            );

            openAdminDashboard();

        }

        // =========================
        // STAFF LOGIN
        // =========================
        else if (controller.isStaff(user)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff Login Successful!"
            );

            openStaffDashboard();

        }

        // =========================
        // UNKNOWN ROLE
        // =========================

        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unknown user role."
            );
        }
    }

    // =========================
    // ADMIN DASHBOARD
    // =========================
    private void openAdminDashboard() {

        MainForm mainForm =
                new MainForm("ADMIN");

        mainForm.setVisible(true);

        dispose();
    }
    private void openStaffDashboard() {

        MainForm mainForm =
                new MainForm("STAFF");

        mainForm.setVisible(true);

        dispose();
    }
   
    

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        LoginForm form =
                new LoginForm();

        form.setVisible(true);
    }
}
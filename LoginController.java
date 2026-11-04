package controller;

import dao.UserDAO;
import model.User;

public class LoginController {

    private UserDAO userDAO;

    // =========================
    // CONSTRUCTOR
    // =========================

    public LoginController() {
        userDAO = new UserDAO();
    }

    // =========================
    // LOGIN
    // =========================

    public User login(
            String username,
            String password) {

        return userDAO.login(
                username,
                password
        );
    }

    // =========================
    // CHECK ADMIN
    // =========================

    public boolean isAdmin(User user) {

        if (user == null) {
            return false;
        }

        return "ADMIN".equalsIgnoreCase(
                user.getRole()
        );
    }

    // =========================
    // CHECK STAFF
    // =========================

    public boolean isStaff(User user) {

        if (user == null) {
            return false;
        }

        return "STAFF".equalsIgnoreCase(
                user.getRole()
        );
    }
}
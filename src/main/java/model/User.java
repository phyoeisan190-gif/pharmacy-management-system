package model;

public class User {

    private int userId;
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(
            int userId,
            String username,
            String password,
            String role) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // =========================
    // GET USER ID
    // =========================

    public int getUserId() {
        return userId;
    }

    // =========================
    // SET USER ID
    // =========================

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // =========================
    // GET USERNAME
    // =========================

    public String getUsername() {
        return username;
    }

    // =========================
    // SET USERNAME
    // =========================

    public void setUsername(String username) {
        this.username = username;
    }

    // =========================
    // GET PASSWORD
    // =========================

    public String getPassword() {
        return password;
    }

    // =========================
    // SET PASSWORD
    // =========================

    public void setPassword(String password) {
        this.password = password;
    }

    // =========================
    // GET ROLE
    // =========================

    public String getRole() {
        return role;
    }

    // =========================
    // SET ROLE
    // =========================

    public void setRole(String role) {
        this.role = role;
    }
}
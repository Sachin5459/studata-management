package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JComboBox<String> userTypeCombo;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;

    private StudentDAO studentDAO;

    public LoginFrame() {
        studentDAO = new StudentDAO();

        setTitle("Login - Student Management");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // ===== User type =====
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Login as:"), gbc);

        gbc.gridx = 1;
        userTypeCombo = new JComboBox<>(new String[]{"Admin", "Student"});
        add(userTypeCombo, gbc);

        // ===== Username / Reg No =====
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username / Reg No:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // ===== Password =====
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // ===== Login button =====
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;

        loginBtn = new JButton("Login");
        add(loginBtn, gbc);

        // ===== Action =====
        loginBtn.addActionListener(e -> doLogin());
    }

    private void doLogin() {
        String userType = (String) userTypeCombo.getSelectedItem();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (userType.equals("Admin")) {
            // Admin login
            if (username.equals("admin") && password.equals("admin123")) { // hardcoded credentials
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                new AdminFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid admin credentials.");
            }
        } else {
            // Student login
            try {
                int regNo;
                try {
                    regNo = Integer.parseInt(username);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Registration number must be numeric.");
                    return;
                }

                Student s = studentDAO.validateStudentLogin(regNo, password);
                if (s != null) {
                    JOptionPane.showMessageDialog(this, "Student login successful!");
                    new StudentFrame(s).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid student credentials.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}








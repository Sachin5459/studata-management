package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AdminFrame extends JFrame {

    private StudentDAO studentDAO;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchBtn, refreshBtn, editBtn, deleteBtn;

    public AdminFrame() {
        studentDAO = new StudentDAO();
        setTitle("Admin Panel - Student Management");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Layout =====
        setLayout(new BorderLayout());

        // ===== Top Panel =====
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        searchField = new JTextField(20);
        searchBtn = new JButton("Search");
        refreshBtn = new JButton("Refresh");

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setForeground(Color.WHITE);

        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(refreshBtn);

        add(topPanel, BorderLayout.NORTH);

        // ===== Table =====
        String[] columns = {"Reg No", "Name", "Marks", "Behaviour", "Attendance", "Fees Status"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // ===== Bottom Panel =====
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);

        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");

        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Load data =====
        loadStudents();

        // ===== Actions =====
        searchBtn.addActionListener(e -> searchStudents());
        refreshBtn.addActionListener(e -> loadStudents());
        editBtn.addActionListener(e -> editStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
    }

    // ===== Load all students =====
    private void loadStudents() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            tableModel.setRowCount(0);
            for (Student s : students) {
                tableModel.addRow(new Object[]{
                        s.getRegNo(),
                        s.getName(),
                        s.getMarks(),
                        s.getBehaviour(),
                        s.getAttendance(),
                        s.getFeesStatus()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage());
        }
    }

    // ===== Search =====
    private void searchStudents() {
        String keyword = searchField.getText().trim();
        try {
            List<Student> students = keyword.isEmpty() ?
                    studentDAO.getAllStudents() :
                    studentDAO.searchStudents(keyword);

            tableModel.setRowCount(0);
            for (Student s : students) {
                tableModel.addRow(new Object[]{
                        s.getRegNo(),
                        s.getName(),
                        s.getMarks(),
                        s.getBehaviour(),
                        s.getAttendance(),
                        s.getFeesStatus()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching students: " + ex.getMessage());
        }
    }

    // ===== Edit =====
    private void editStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first.");
            return;
        }
        int regNo = (int) tableModel.getValueAt(row, 0);

        try {
            Student s = studentDAO.getStudentByRegNo(regNo);
            if (s == null) return;

            String name = JOptionPane.showInputDialog(this, "Name:", s.getName());
            double marks = Double.parseDouble(JOptionPane.showInputDialog(this, "Marks:", s.getMarks()));
            String behaviour = JOptionPane.showInputDialog(this, "Behaviour:", s.getBehaviour());
            double attendance = Double.parseDouble(JOptionPane.showInputDialog(this, "Attendance:", s.getAttendance()));
            String fees = JOptionPane.showInputDialog(this, "Fees Status:", s.getFeesStatus());

            s.setName(name);
            s.setMarks(marks);
            s.setBehaviour(behaviour);
            s.setAttendance(attendance);
            s.setFeesStatus(fees);

            if (studentDAO.updateStudent(s)) {
                JOptionPane.showMessageDialog(this, "Updated successfully.");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // ===== Delete =====
    private void deleteStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first.");
            return;
        }
        int regNo = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete student " + regNo + "?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (studentDAO.deleteStudent(regNo)) {
                    JOptionPane.showMessageDialog(this, "Deleted successfully.");
                    loadStudents();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminFrame().setVisible(true));
    }
}







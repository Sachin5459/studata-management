package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class StudentFrame extends JFrame {

    private Student student;

    public StudentFrame(Student student) {
        this.student = student;

        setTitle("Student Panel - " + student.getName());
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // ===== Reg No =====
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Reg No:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(String.valueOf(student.getRegNo())), gbc);
        row++;

        // ===== Name =====
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(student.getName()), gbc);
        row++;

        // ===== Marks =====
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Marks:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(String.valueOf(student.getMarks())), gbc);
        row++;

        // ===== Behaviour =====
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Behaviour:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(student.getBehaviour()), gbc);
        row++;

        // ===== Attendance =====
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Attendance:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(String.valueOf(student.getAttendance())), gbc);
        row++;

        // ===== Fees Status =====
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Fees Status:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(student.getFeesStatus()), gbc);
    }
}

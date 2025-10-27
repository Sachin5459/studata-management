package dao;

import model.Student;
import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ===== Validate student login and return Student object =====
    public Student validateStudentLogin(int regNo, String password) throws SQLException {
        String sql = "SELECT * FROM students WHERE reg_no = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, regNo);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("reg_no"),
                            rs.getString("name"),
                            rs.getDouble("marks"),
                            rs.getString("behaviour"),
                            rs.getDouble("attendance"),
                            rs.getString("fees_status"),
                            rs.getString("password")
                    );
                } else {
                    return null; // login failed
                }
            }
        }
    }

    // ===== Get all students =====
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("reg_no"),
                        rs.getString("name"),
                        rs.getDouble("marks"),
                        rs.getString("behaviour"),
                        rs.getDouble("attendance"),
                        rs.getString("fees_status"),
                        rs.getString("password")
                ));
            }
        }
        return students;
    }

    // ===== Search students by keyword (name or reg no) =====
    public List<Student> searchStudents(String keyword) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR reg_no LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("reg_no"),
                            rs.getString("name"),
                            rs.getDouble("marks"),
                            rs.getString("behaviour"),
                            rs.getDouble("attendance"),
                            rs.getString("fees_status"),
                            rs.getString("password")
                    ));
                }
            }
        }
        return students;
    }

    // ===== Get student by reg no =====
    public Student getStudentByRegNo(int regNo) throws SQLException {
        String sql = "SELECT * FROM students WHERE reg_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, regNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("reg_no"),
                            rs.getString("name"),
                            rs.getDouble("marks"),
                            rs.getString("behaviour"),
                            rs.getDouble("attendance"),
                            rs.getString("fees_status"),
                            rs.getString("password")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    // ===== Update student =====
    public boolean updateStudent(Student s) throws SQLException {
        String sql = "UPDATE students SET name = ?, marks = ?, behaviour = ?, attendance = ?, fees_status = ? WHERE reg_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setDouble(2, s.getMarks());
            ps.setString(3, s.getBehaviour());
            ps.setDouble(4, s.getAttendance());
            ps.setString(5, s.getFeesStatus());
            ps.setInt(6, s.getRegNo());

            return ps.executeUpdate() > 0;
        }
    }

    // ===== Delete student =====
    public boolean deleteStudent(int regNo) throws SQLException {
        String sql = "DELETE FROM students WHERE reg_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, regNo);
            return ps.executeUpdate() > 0;
        }
    }

    // ===== Add student =====
    public boolean addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students(reg_no, name, marks, behaviour, attendance, fees_status, password) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getRegNo());
            ps.setString(2, s.getName());
            ps.setDouble(3, s.getMarks());
            ps.setString(4, s.getBehaviour());
            ps.setDouble(5, s.getAttendance());
            ps.setString(6, s.getFeesStatus());
            ps.setString(7, s.getPassword());

            return ps.executeUpdate() > 0;
        }
    }
}





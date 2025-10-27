package model;

public class Student {
    private int regNo;
    private String name;
    private double marks;
    private String behaviour;
    private double attendance;
    private String feesStatus;
    private String password;

    // ✅ Full constructor (used when fetching from DB)
    public Student(int regNo, String name, double marks, String behaviour, double attendance, String feesStatus, String password) {
        this.regNo = regNo;
        this.name = name;
        this.marks = marks;
        this.behaviour = behaviour;
        this.attendance = attendance;
        this.feesStatus = feesStatus;
        this.password = password;
    }

    // ✅ Constructor without password (for display/editing)
    public Student(int regNo, String name, double marks, String behaviour, double attendance, String feesStatus) {
        this.regNo = regNo;
        this.name = name;
        this.marks = marks;
        this.behaviour = behaviour;
        this.attendance = attendance;
        this.feesStatus = feesStatus;
    }

    // ✅ Empty constructor
    public Student() {}

    // ====== Getters and Setters ======

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public String getFeesStatus() {
        return feesStatus;
    }

    public void setFeesStatus(String feesStatus) {
        this.feesStatus = feesStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ Optional: For debugging/logs
    @Override
    public String toString() {
        return "Student{" +
                "regNo=" + regNo +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", behaviour='" + behaviour + '\'' +
                ", attendance=" + attendance +
                ", feesStatus='" + feesStatus + '\'' +
                '}';
    }
}



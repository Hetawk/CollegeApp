package com.ekd.college.control.DBHandlers;
//package com.example.studentmanagementsystem.DBHandlers;


public class StudentModel {
    private  String studentID;
    private String studentName;
    private String school;
    private String dob;
    private String contactNo;
    private String address;
    private String gName;
    private String gcNo;
    private String password;
    private String studentEmail;
    public StudentModel(){

    }


    public StudentModel(String studentID, String studentName,
                        String school, String dob, String contactNo, String address,
                        String gName, String gcNo, String password, String studentEmail) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.school = school;
        this.dob = dob;
        this.contactNo = contactNo;
        this.address = address;
        this.gName = gName;
        this.gcNo = gcNo;
        this.password = password;
        this.studentEmail = studentEmail;
    }

    public String getStudentID() {return studentID;}
    public void setStudentID(String studentID) {this.studentID = studentID; }

    public String getStudentName() {return studentName; }
    public void setStudentName(String studentName) {this.studentName = studentName;}

    public String getSchool() {return school;}
    public void setSchool(String school) {this.school = school;}

    public String getDob() {return dob; }
    public void setDob(String dob) { this.dob = dob;}

    public String getContactNo() { return contactNo;}
    public void setContactNo(String contactNo) {this.contactNo = contactNo;}

    public String getAddress() {return address; }
    public void setAddress(String address) {this.address = address;}

    public String getgName() {return gName;}
    public void setgName(String gName) {this.gName = gName; }

    public String getGcNo() {return gcNo;  }
    public void setGcNo(String gcNo) {this.gcNo = gcNo; }

    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password;}

    public String getStudentEmail() {return studentEmail;}
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail;}


}

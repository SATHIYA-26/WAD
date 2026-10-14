package com.example.courseregistration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registrations")
public class CourseRegistration {
    @Id
    private String id;

    private String studentName;
    private String registerNumber;
    private String email;
    private String department;
    private String courseName;

    public CourseRegistration() {
    }

    public CourseRegistration(String studentName, String registerNumber,
                              String email, String department, String courseName) {
        this.studentName = studentName;
        this.registerNumber = registerNumber;
        this.email = email;
        this.department = department;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
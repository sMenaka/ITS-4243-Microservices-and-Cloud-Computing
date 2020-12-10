package com.student.courseCatalog;
import java.math.BigInteger;

public class Student {
    private BigInteger studentid;
    private BigInteger courseid;
    private String studentname;
    private BigInteger year;

    public Student() {
    }

    public BigInteger getStudentid() {
        return studentid;
    }

    public void setStudentid(BigInteger studentid) {
        this.studentid = studentid;
    }

    public BigInteger getCourseid() {
        return courseid;
    }

    public void setCourseid(BigInteger courseid) {
        this.courseid = courseid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public BigInteger getYear() {
        return year;
    }

    public void setYear(BigInteger year) {
        this.year = year;
    }
}


package org.example.model;
import java.util.*;
public class Student {
    private Integer studentid;
    private String studentname;
    private Set<Course> courses = new HashSet<Course>();
    public Student() {
    }
    public Student(String studentname) {
        this.studentname = studentname;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString(){
        return "Student{" +
                "id=" + studentid +
                ", name='" + studentname + '\'' +
                '}';
    }
}

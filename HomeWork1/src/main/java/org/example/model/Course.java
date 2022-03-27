package org.example.model;
import java.util.*;
public class Course {

    private Integer courseid;
    private String coursename;
    private Set<Student> students = new HashSet<Student>();
    public Course() {
    }
    public Course(String coursename) {
        this.coursename = coursename;
    }
    public Integer getCourseID() {
        return courseid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString(){
        return "Course{" +
                "id=" + courseid +
                ", name='" + coursename + '\'' +
                '}';
    }



}

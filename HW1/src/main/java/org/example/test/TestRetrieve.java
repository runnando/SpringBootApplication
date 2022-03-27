package org.example.test;

import org.example.model.Course;
import org.example.model.Student;
import org.example.util.HibernateUtils;
import org.hibernate.Session;

public class TestRetrieve {
    public static void main(String[] args){
        Session session = HibernateUtils.openSession();

        // Retrieve how many courses a student take
        Student s1 = (Student) session.get(Student.class,4);
        System.out.println("--------------------------------------------");
        System.out.println("Student ID: " + s1.getStudentid() + " | " + "Student Name: " + s1.getStudentname());
        for(Course c: s1.getCourses()){
            System.out.println("Course ID: " + c.getCourseID() + " | " + "Course Name: " + c.getCoursename());
        }
        System.out.println("--------------------------------------------");

        // Retrieve how many student take this course
        Course c1 = (Course) session.get(Course.class, 2);
        System.out.println("--------------------------------------------");
        System.out.println("Course ID: " + c1.getCourseID() + " | " + "Course Name: " + c1.getCoursename());
        for(Student s: c1.getStudents()){
            System.out.println("Student ID: " + s.getStudentid() + " | " + "Student Name: " + s.getStudentname());
        }
        System.out.println("--------------------------------------------");

        session.close();;
    }

}

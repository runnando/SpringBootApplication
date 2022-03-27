package org.example.test;

import org.example.model.Course;
import org.example.model.Student;
import org.example.util.HibernateUtils;
import org.hibernate.Session;

public class TestDelete {
    public static void main(String[] args){
        Session session = HibernateUtils.openSession();
        session.getTransaction().begin();
        Student s1 = (Student) session.get(Student.class, 1);
        System.out.println("Student ID: " + s1.getStudentid() + " | " + "Student Name: " + s1.getStudentname());
        s1.getCourses().clear();

        Student s2 = (Student) session.get(Student.class, 2);
        Course c2 = new Course();
        c2.setCourseid(2);
        session.delete(s1);
        //In Student.hbm.xml, I set cascade to save-update; use delete() will delete everything relate to this student,
        //
        // even in the course table, the course he select will also be delete
        //session.delete(s1);
        session.getTransaction().commit();
        session.close();
    }
}

package org.example.test;

import org.example.model.Course;
import org.example.model.Student;
import org.example.util.HibernateUtils;
import org.hibernate.Session;

public class TestInsertUpdate {
    public static void main(String[] args){
        Session session = HibernateUtils.openSession();
        Course c1 = new Course("Java Basic");
        Course c2 = new Course("Java Advance");
        Course c3 = new Course("DevOps Basic");
        Course c4 = new Course("Azure Data Basic");

        Student s1 = new Student("Tony");
        Student s2 = new Student("Tom");
        Student s3 = new Student("Lilly");
        Student s4 = new Student("Elden");

        ////Add course to student
        s1.getCourses().add(c1);
        s1.getCourses().add(c2);

        s2.getCourses().add(c2);
        s2.getCourses().add(c3);

        s3.getCourses().add(c3);
        s3.getCourses().add(c4);

        s4.getCourses().add(c1);
        s4.getCourses().add(c2);
        s4.getCourses().add(c3);
        s4.getCourses().add(c4);

        //Add student to course
        c1.getStudents().add(s1);
        c1.getStudents().add(s4);

        c2.getStudents().add(s1);
        c2.getStudents().add(s2);
        c2.getStudents().add(s4);

        c3.getStudents().add(s2);
        c3.getStudents().add(s3);
        c3.getStudents().add(s4);

        c4.getStudents().add(s3);
        c4.getStudents().add(s4);

        //For student_course table, sid is foreign key, because I set inverse = true in Course xml file
        // Begin transaction
        session.getTransaction().begin();
        System.out.println("-------------------------Insert Student-------------------------");
        session.save(s1);
        session.save(s2);
        session.save(s3);
        session.save(s4);

        // In student.hbm.xml file, I set cascade to save-update, when save student data, it will automaticly save course data
//        System.out.println("-------------------------Insert Course-------------------------");
//        session.save(c1);
//        session.save(c2);
//        session.save(c3);
//        session.save(c4);


        System.out.println("-------------------------Update Student-------------------------");

        // Submit transaction
        session.getTransaction().commit();
        session.close();

    }




}

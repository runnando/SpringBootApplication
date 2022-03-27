package org.example.test;
import org.hibernate.Session;
import org.example.util.HibernateUtils;
import org.example.model.Course;
import org.example.model.Student;
public class TestCreate {
    public static void main(String[] args) {

        Session session = HibernateUtils.openSession();
        session.close();

//
    }

}

package org.example.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        //read hibernate.cfg.xml
        Configuration configuration = new Configuration().configure();

        //acquire sessionFactory
        sessionFactory = configuration.buildSessionFactory();

        //release sessionFactory resource
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                sessionFactory.close();
                System.out.println("Release sessionFactory...");
            }
        });
    }


    public static Session openSession(){
        return sessionFactory.openSession();
    }


    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}

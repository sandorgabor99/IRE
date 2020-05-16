package sg.bank.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("init cause : \n" +e.initCause(e).getMessage());
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(String.valueOf(e));
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}

package lk.ijse.config;

import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfiguration {

    private static SessionFactoryConfiguration sessionFactoryConfiguration;

    private SessionFactory session;

    private SessionFactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Customer.class);
        session = configuration.buildSessionFactory();
    }
    public static SessionFactoryConfiguration getInstance(){
        if (sessionFactoryConfiguration == null){
            sessionFactoryConfiguration = new SessionFactoryConfiguration();
        }
        return sessionFactoryConfiguration;
    }
    public Session getSession() {
        return session.openSession();
    }
}

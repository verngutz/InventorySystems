package system.dao.impl;

import org.hibernate.SessionFactory;

public class SessionFactorySingleton {
	private static SessionFactory sessionFactory;
    
    public static void setSessionFactory(SessionFactory factory)
    {
        sessionFactory = factory;
    }    
    
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}

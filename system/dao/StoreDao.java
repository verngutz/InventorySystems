package system.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import system.Store;

public class StoreDao 
{
	public void delete(Store s) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.delete(s);

            tx.commit(); 
        }
        catch(HibernateException ex) 
        {
            ex.printStackTrace();
            tx.rollback();
        } 
        finally 
        {
            session.close();
        }
	}

	public void delete(int id) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Store s = (Store)session.get(Store.class, id);
            session.delete(s);

            tx.commit(); 
        }
        catch(HibernateException ex) 
        {
            ex.printStackTrace();
            tx.rollback();
        } 
        finally 
        {
            session.close();
        }
	}

	public void save(Store s) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.saveOrUpdate(s);

            tx.commit(); 
        }
        catch(HibernateException ex) 
        {
            ex.printStackTrace();
            tx.rollback();
        } 
        finally 
        {
            session.close();
        }
	}
	
	public Store get(int id)
	{
		Session session = null;
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            return (Store) session.get(Store.class, id);

        }
        finally 
        {
            session.close();
        }
	}

	@SuppressWarnings("unchecked")
	public List<Store> getStores()
    {
        Session session = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            Query q = session.createQuery("from Store");
            List<Store> results = q.list();            
            return results;
        }
        finally 
        {
            session.close();
        }
    }
}

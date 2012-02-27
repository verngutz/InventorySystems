package system.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import system.TransactionItem;

public class TransactionItemDao 
{
	public void delete(TransactionItem ti) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.delete(ti);

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
            
            TransactionItem ti = (TransactionItem) session.get(TransactionItem.class, id);
            session.delete(ti);

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

	public void save(TransactionItem ti) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.saveOrUpdate(ti);

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
	
	public TransactionItem get(int id)
	{
		Session session = null;
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            return (TransactionItem) session.get(TransactionItem.class, id);

        }
        finally 
        {
            session.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<TransactionItem> getTransactionItems()
    {
        Session session = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            Query q = session.createQuery("from TransactionItem");
            List<TransactionItem> results = q.list();            
            return results;
        }
        finally 
        {
            session.close();
        }
    }
}

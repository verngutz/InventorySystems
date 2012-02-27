package system.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import system.Item;

public class ItemDao 
{
	public void delete(Item it) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.delete(it);

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

	public void delete(String itemCode) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Item it = (Item)session.get(Item.class, itemCode);
            session.delete(it);

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

	public void save(Item it) 
	{
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.saveOrUpdate(it);

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

	public Item get(String itemCode)
	{
		Session session = null;
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            return (Item) session.get(Item.class, itemCode);

        }
        finally 
        {
            session.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getItems()
    {
        Session session = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            Query q = session.createQuery("from Item");
            List<Item> results = q.list();            
            return results;
        }
        finally 
        {
            session.close();
        }
    }
}

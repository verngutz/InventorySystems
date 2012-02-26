package system.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import system.dao.*;
import system.*;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public void delete(TransactionE t) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.delete(t);

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

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            TransactionE t = (TransactionE)session.get(TransactionE.class,id);
            session.delete(t);

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

	@Override
	public void save(TransactionE t) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.saveOrUpdate(t);

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
	
	public TransactionE get(int id){
		Session session = null;
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            return (TransactionE) session.get(TransactionE.class, id);

        }
        finally 
        {
            session.close();
        }
	}
	
	public List<TransactionE> getTransactions()
    {
        Session session = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            Query q = session.createQuery("from TransactionE");
            List results = q.list();            
            return results;
        }
        finally 
        {
            session.close();
        }
    }
}

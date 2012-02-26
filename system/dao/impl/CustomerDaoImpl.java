package system.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import system.dao.CustomerDao;
import system.Cashier;
import system.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void delete(Customer c) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.delete(c);

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
            
            Customer c = (Customer) session.get(Customer.class, id);
            session.delete(c);

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
	public void save(Customer c) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.saveOrUpdate(c);

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
	
	public Customer get(int id){
		Session session = null;
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            return (Customer) session.get(Customer.class, id);

        }
        finally 
        {
            session.close();
        }
	}
	public List<Customer> getCustomers()
    {
        Session session = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            Query q = session.createQuery("from Customer");
            List results = q.list();            
            return results;
        }
        finally 
        {
            session.close();
        }
    }
}

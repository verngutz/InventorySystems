package system.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import system.dao.DeliveryDao;
import system.Delivery;

public class DeliveryDaoImpl implements DeliveryDao {

	@Override
	public void delete(Delivery d) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.delete(d);

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
            
            Delivery d = (Delivery) session.get(Delivery.class, id);
            session.delete(d);

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
	public void save(Delivery d) {
		// TODO Auto-generated method stub
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            session.saveOrUpdate(d);

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

}

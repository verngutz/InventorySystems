
import java.io.File;
import java.util.HashMap;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import system.dao.*;
import system.dao.impl.*;
import system.entity.*;

public class HibernateTest
{
    public static void main(String[] args)
    {
        Configuration configuration = new Configuration();
        configuration.configure(new File("hibernate.cfg.xml"));
        SessionFactory factory = configuration.buildSessionFactory();
        
        // set the singleton do all the dao objects can have easy access to it
        SessionFactorySingleton.setSessionFactory(factory);
                
        testSaveItem();
        //testSaveCustomer();
        //testGet();
       
    	// WARNING:
    	// deleting objects that are referred to by other objects
    	// results in foreign key constraint violations
    	
    	// to get around it you will need to clear the references to
    	// the to be deleted object BEFORE actually deleting it
    		// this can be done on a per object basis, e.g. per AuctionItem
    		// or via an HQL batch update which will hit all objects at once    	
        
        //clearReferences(bid);
        //testDelete(bid);

        //testGet();

        SessionFactorySingleton.getSessionFactory().close();
    }
    
    private static void testSaveCustomer() {
		// TODO Auto-generated method stub
    	System.out.println("SAVE C");
    	CustomerDao cusdao = new CustomerDaoImpl();
    	
    	Customer c = new Customer();
    	c.setFirstName("vern");
    	c.setLastName("gucci");
    	
    	cusdao.save(c);
	}

	private static void testSaveItem(){
    	System.out.println("SAVE");
    	ItemDao itemdao = new ItemDaoImpl();
    	
    	Item item = new Item();
    	item.setItemCode("P1");
    	item.setItemName("Egg");
    	item.setUnitName("Dozen");
    	item.setItemCategory("Produce");
    	item.setUnitPrice(100.0);
    	
    	itemdao.save(item);
    }
    /*
    private static Bid bid;
    
    private static void testSaveAuctions()
    {
    	System.out.println();
    	System.out.println("SAVE");

        IAuctionItemDao auctionItemDao = new AuctionItemDaoImpl();
        
        // create auctionItems and bids
        AuctionItem auctionItem = new AuctionItem();
        auctionItem.setDescription("Jurassic Park");
        auctionItem.setType("Blu-ray");
        HashMap hp = new HashMap();
        
        bid = new Bid();
        bid.setAmount(10.0);
        hp.put("shit", bid);
        hp.put("asdasd", bid);
        auctionItem.setBids(hp);
        auctionItemDao.save(auctionItem);
        
        AuctionItem auctionItem2 = new AuctionItem();
        auctionItem2.setDescription("Jurassic Park: The Lost World");
        auctionItem2.setType("Blu-ray");
        
        auctionItemDao.save(auctionItem2);
    }

    private static void testDelete(Bid b)
    {
    	System.out.println();
    	System.out.println("DELETE");

        IBidDao bidDao = new BidDaoImpl();
        bidDao.delete(b);
    }

    private static void clearReferences(Bid b)
    {
    	System.out.println();
    	System.out.println("CLEAR");

        IAuctionItemDao auctionItemDao = new AuctionItemDaoImpl();
        auctionItemDao.clearReferences(b);
    }

    private static void testGet()
    {
    	System.out.println();
    	System.out.println("GET");

        IAuctionItemDao auctionItemDao = new AuctionItemDaoImpl();
        System.out.println(auctionItemDao.getAuctionItems());
    }*/
}
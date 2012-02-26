package system.dao;

import system.*;

public interface CustomerDao {
	public void save(Customer c);
	public void delete(Customer c);
	public void delete(int id);
}

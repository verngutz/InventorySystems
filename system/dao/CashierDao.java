package system.dao;

import system.entity.Cashier;

public interface CashierDao {
	public void save(Cashier c);
	public void delete(Cashier c);
	public void delete(int id);
}

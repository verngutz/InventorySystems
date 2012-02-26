package system.dao;

import system.entity.*;

public interface TransactionItemDao {
	public void save(TransactionItem ti);
	public void delete(TransactionItem ti);
	public void delete(int id);
}

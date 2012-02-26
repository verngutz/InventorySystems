package system.dao;

import system.*;

public interface TransactionItemDao {
	public void save(TransactionItem ti);
	public void delete(TransactionItem ti);
	public void delete(int id);
}

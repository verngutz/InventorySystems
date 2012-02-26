package system.dao;

import system.entity.TransactionE;

public interface TransactionDao {
	public void save(TransactionE t);
	public void delete(TransactionE t);
	public void delete(int id);
}

package system.dao;

import system.Store;

public interface StoreDao {
	public void save(Store s);
	public void delete(Store s);
	public void delete(int id);
}

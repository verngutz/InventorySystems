package system.dao;

import system.Item;

public interface ItemDao {
	public void save(Item it);
	public void delete(Item it);
	public void delete(String itemCode);
}

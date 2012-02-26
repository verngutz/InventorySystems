package system.dao;

import system.entity.Item;

public interface ItemDao {
	public void save(Item it);
	public void delete(Item it);
	public void delete(String itemCode);
}

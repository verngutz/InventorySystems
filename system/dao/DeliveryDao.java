package system.dao;

import system.Delivery;

public interface DeliveryDao {
	public void save(Delivery d);
	public void delete(Delivery d);
	public void delete(int id);
}

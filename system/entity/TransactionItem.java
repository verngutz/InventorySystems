package system.entity;

import system.entity.Item;

public class TransactionItem
{
	private int id;
	private Item item;
	private int quantity;
	private double price;
	
	public TransactionItem(int id, Item item, int quantity, double price){
		this.setId(id);
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	public TransactionItem(Item item, int quantity, double price){
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	//GETTERS AND SETTERS
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

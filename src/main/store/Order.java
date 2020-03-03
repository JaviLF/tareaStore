package store;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private Customer customer;
	private Salesman salesman;
	private Date orderedOn;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryCountry;
	private Set<OrderItem> items;

	public Order(Customer customer, Salesman salesman, String deliveryStreet, String deliveryCity, String deliveryCountry, Date orderedOn) {
		this.customer = customer;
		this.salesman = salesman;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryCountry = deliveryCountry;
		this.orderedOn = orderedOn;
		this.items = new HashSet<OrderItem>();
	}

	public Customer getCustomer() {
		return customer;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public Date getOrderedOn() {
		return orderedOn;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public String getDeliveryCountry() {
		return deliveryCountry;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public float total() {
		float totalItems = 0;
		for (OrderItem item : items) {
			float totalItem=0;
			float itemAmount = item.getProduct().getUnitPrice() * item.getQuantity();
			if (isAccesory(item))
				totalItem = applyBookDiscount(itemAmount);
			if (isBike(item)) 
				totalItem = applyBikeDiscount(itemAmount);
			if (isCloath(item))
				totalItem = applyCloathDiscount(item, itemAmount);
			totalItems += totalItem;
		}

		if (this.deliveryCountry == "USA"){
			// total=totalItems + tax + 0 shipping
			return totalItems + totalItems * 5 / 100;
		}

		// total=totalItemst + tax + 15 shipping
		return totalItems + totalItems * 5 / 100 + 15;
	}

	private float applyCloathDiscount(OrderItem item, float itemAmount) {
		float totalItem;
		float cloathingDiscount = 0;
		if (item.getQuantity() > 2) {
			cloathingDiscount = item.getProduct().getUnitPrice();
		}
		totalItem = itemAmount - cloathingDiscount;
		return totalItem;
	}

	private float applyBikeDiscount(float itemAmount) {
		float totalItem;
		// 20% discount for Bikes
		totalItem = itemAmount - itemAmount * 20 / 100;
		return totalItem;
	}

	private float applyBookDiscount(float itemAmount) {
		float totalItem;
		float booksDiscount = 0;
		if (itemAmount >= 100) {
			booksDiscount = itemAmount * 10 / 100;
		}
		totalItem = itemAmount - booksDiscount;
		return totalItem;
	}

	private boolean isCloath(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Cloathing;
	}

	private boolean isBike(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Bikes;
	}

	private boolean isAccesory(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Accessories;
	}
}

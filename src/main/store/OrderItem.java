package store;

public class OrderItem {
	
	private Product product;
	private int quantity;

	/*
	 * Order Item Constructor
	 */
	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() { 
		return quantity;
	}
 
	float calculateTotalForItem() {
		float totalItem=0;
		float discount = 0;
		if (getProduct().getCategory() == ProductCategory.Accessories) {
			discount = calculateDiscountForAccessories();
		}
		if (getProduct().getCategory() == ProductCategory.Bikes) {
			discount = calculateDiscountForBikes();
		}
		if (getProduct().getCategory() == ProductCategory.Cloathing) {
			discount = calculateDiscountForCloathing();
		}
		totalItem = calculateTotalAmount() - discount;
		return totalItem;
	}

	private float calculateDiscountForCloathing() {
		float cloathingDiscount = 0;
		if (getQuantity() > 2) {
			cloathingDiscount = getProduct().getUnitPrice();
		}
		return cloathingDiscount;
	}

	private float calculateDiscountForBikes() {
		return calculateTotalAmount() * 20 / 100;
	}

	private float calculateDiscountForAccessories() {
		float booksDiscount = 0;
		if (calculateTotalAmount() >= 100) {
			booksDiscount = calculateTotalAmount() * 10 / 100;
		}
		return booksDiscount;
	}

	private float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}
 
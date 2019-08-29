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
		DiscountCalculator discountCalculator = null;
		if (getProduct().getCategory() == ProductCategory.Accessories) {
			discountCalculator = new AccessoriesDiscount();
		}
		if (getProduct().getCategory() == ProductCategory.Bikes) {
			discountCalculator = new BikesDiscount();
		}
		if (getProduct().getCategory() == ProductCategory.Cloathing) {
			discountCalculator = new CloathingDiscount();
		}
		discount = discountCalculator.calculateDiscount(this);
		totalItem = calculateTotalAmount() - discount;
		return totalItem;
	}

	float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}
 
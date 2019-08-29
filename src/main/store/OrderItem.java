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
			DiscountCalculator accessoriesDiscount = new AccessoriesDiscount();
			discount = accessoriesDiscount.calculateDiscount(this);
		}
		if (getProduct().getCategory() == ProductCategory.Bikes) {
			DiscountCalculator bikesDiscount = new BikesDiscount();
			discount = bikesDiscount.calculateDiscount(this);
		}
		if (getProduct().getCategory() == ProductCategory.Cloathing) {
			DiscountCalculator cloathingDiscountC = new CloathingDiscount();
			discount = cloathingDiscountC.calculateDiscount(this);
		}
		totalItem = calculateTotalAmount() - discount;
		return totalItem;
	}

	float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}
 
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
			AccessoriesDiscount accessoriesDiscount = new AccessoriesDiscount();
			discount = accessoriesDiscount.calculateDiscountForAccessories(this);
		}
		if (getProduct().getCategory() == ProductCategory.Bikes) {
			BikesDiscount bikesDiscount = new BikesDiscount();
			discount = bikesDiscount.calculateDiscountForBikes(this);
		}
		if (getProduct().getCategory() == ProductCategory.Cloathing) {
			CloathingDiscount cloathingDiscountC = new CloathingDiscount();
			discount = cloathingDiscountC.calculateDiscountForCloathing(this);
		}
		totalItem = calculateTotalAmount() - discount;
		return totalItem;
	}

	float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}
 
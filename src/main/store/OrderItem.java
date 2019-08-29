package store;

import java.util.HashMap;
import java.util.Map;

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
		DiscountCalculator discountCalculator = createDiscountCalculator();
		discount = discountCalculator.calculateDiscount(this);
		totalItem = calculateTotalAmount() - discount;
		return totalItem;
	}

	private DiscountCalculator createDiscountCalculator() {
		Map<ProductCategory, DiscountCalculator> map = 
				new HashMap<ProductCategory, DiscountCalculator>();
		DiscountCalculator discountCalculator = null;
		
		map.put(ProductCategory.Accessories, new AccessoriesDiscount());
		map.put(ProductCategory.Bikes, new BikesDiscount());
		map.put(ProductCategory.Cloathing, new CloathingDiscount());
		
		if (getProduct().getCategory() == ProductCategory.Accessories) {
			discountCalculator = new AccessoriesDiscount();
		}
		if (getProduct().getCategory() == ProductCategory.Bikes) {
			discountCalculator = new BikesDiscount();
		}
		if (getProduct().getCategory() == ProductCategory.Cloathing) {
			discountCalculator = new CloathingDiscount();
		}
		return map.get(getProduct().getCategory());
	}

	float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}
 
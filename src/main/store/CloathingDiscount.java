package store;

public class CloathingDiscount {

	float calculateDiscountForCloathing(OrderItem orderItem) {
		float cloathingDiscount = 0;
		if (orderItem.getQuantity() > 2) {
			cloathingDiscount = orderItem.getProduct().getUnitPrice();
		}
		return cloathingDiscount;
	}

}

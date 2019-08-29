package store;

public class BikesDiscount implements DiscountCalculator {

	public float calculateDiscount(OrderItem orderItem) {
		return orderItem.calculateTotalAmount() * 20 / 100;
	}

}

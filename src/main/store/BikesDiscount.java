package store;

public class BikesDiscount {

	float calculateDiscountForBikes(OrderItem orderItem) {
		return orderItem.calculateTotalAmount() * 20 / 100;
	}

}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderItemTest {

    // Test cases for product with discount
    @Test
    public void testGetFinalPrice_ProductWithDiscount() {
        Fruit fruit = new Fruit("Apple", 10000, 10, true);
        int quantity = 5;
        int expectedFinalPrice = 35000; // Price with 30% discount for local fruit
        OrderItem orderItem = new OrderItem(fruit, quantity);
        int finalPrice = orderItem.getFinalPrice();
        Assertions.assertEquals(expectedFinalPrice, finalPrice);
    }

    // Test cases for product without discount
    @Test
    public void testGetFinalPrice_ProductWithoutDiscount() {
        Veggie veggie = new Veggie("Tomato", 8000, 15, true);
        int quantity = 2;
        int expectedFinalPrice = 16000; // No discount for 2 quantity of organic veggie
        OrderItem orderItem = new OrderItem(veggie, quantity);
        int finalPrice = orderItem.getFinalPrice();
        Assertions.assertEquals(expectedFinalPrice, finalPrice);
    }

    // Test cases for premium customer with discount
    @Test
    public void testGetFinalPrice_PremiumCustomerWithDiscount() {
        Fruit fruit = new Fruit("Mango", 10000, 10, false);
        int quantity = 6;
        int expectedFinalPrice = 48000; // Price with 20% discount for non-local fruit
        Customer premiumCustomer = new Customer("Basyir Haykal", true);
        OrderItem orderItem = new OrderItem(fruit, quantity);
        Cart cart = new Cart(premiumCustomer);
        cart.addOrderItem(orderItem);
        int finalPrice = orderItem.getFinalPrice();
        Assertions.assertEquals(expectedFinalPrice, finalPrice);
    }
}

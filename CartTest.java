import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest {

    // Test cases for cart with premium customer and total price > 300_000
    @Test
    public void testGetTotalPrice_PremiumCustomer_TotalPriceGreaterThanThreshold() {
        Fruit fruit = new Fruit("Apple", 10000, 10, true);
        OrderItem orderItem = new OrderItem(fruit, 10);
        Customer premiumCustomer = new Customer("Haykal Basyir", true);
        Cart cart = new Cart(premiumCustomer);
        cart.addOrderItem(orderItem);
        int totalPrice = cart.getTotalPrice();
        int expectedTotalPrice = (int) (orderItem.getFinalPrice());
        Assertions.assertEquals(expectedTotalPrice, totalPrice);
    }

    // Test cases for cart with premium customer and total price <= 300_000
    @Test
    public void testGetTotalPrice_PremiumCustomer_TotalPriceLessThanOrEqualThreshold() {
        Fruit fruit = new Fruit("Mango", 10000, 10, true);
        OrderItem orderItem = new OrderItem(fruit, 2);
        Customer premiumCustomer = new Customer("Annisa Humaira", true);
        Cart cart = new Cart(premiumCustomer);
        cart.addOrderItem(orderItem);
        int totalPrice = cart.getTotalPrice();
        int expectedTotalPrice = orderItem.getFinalPrice();
        Assertions.assertEquals(expectedTotalPrice, totalPrice);
    }

    // Test cases for cart with regular customer
    @Test
    public void testGetTotalPrice_RegularCustomer() {
        Veggie veggie = new Veggie("Carrot", 8000, 15, true);
        OrderItem orderItem1 = new OrderItem(veggie, 5);
        OrderItem orderItem2 = new OrderItem(veggie, 3);
        Customer regularCustomer = new Customer("Humaira Annisa", false);
        Cart cart = new Cart(regularCustomer);
        cart.addOrderItem(orderItem1);
        cart.addOrderItem(orderItem2);
        int totalPrice = cart.getTotalPrice();
        int expectedTotalPrice = orderItem1.getFinalPrice() + orderItem2.getFinalPrice();
        Assertions.assertEquals(expectedTotalPrice, totalPrice);
    }

    // Test cases for cart with no items
    @Test
    public void testGetTotalPrice_EmptyCart() {
        Customer regularCustomer = new Customer("Cristiano Ronaldo", false);
        Cart cart = new Cart(regularCustomer);
        int totalPrice = cart.getTotalPrice();
        Assertions.assertEquals(0, totalPrice);
    }
}

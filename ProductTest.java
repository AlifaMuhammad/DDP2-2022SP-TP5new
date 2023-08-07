import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    // Test cases for Fruit class
    @Test
    public void testFruitDiscount_QuantityGreaterThanEqualTo5() {
        Fruit fruit = new Fruit("Apple", 10000, 10, true);
        int discount = fruit.checkDiscount(5);
        Assertions.assertEquals(30, discount);
    }

    @Test
    public void testFruitDiscount_QuantityEqualTo3() {
        Fruit fruit = new Fruit("Grape", 10000, 10, true);
        int discount = fruit.checkDiscount(3);
        Assertions.assertEquals(20, discount);
    }

    @Test
    public void testFruitDiscount_QuantityLessThan3() {
        Fruit fruit = new Fruit("Mango", 10000, 10, true);
        int discount = fruit.checkDiscount(2);
        Assertions.assertEquals(0, discount);
    }

    // Test cases for Veggie class
    @Test
    public void testVeggieDiscount_QuantityGreaterThanEqualTo5() {
        Veggie veggie = new Veggie("Carrot", 8000, 15, true);
        int discount = veggie.checkDiscount(5);
        Assertions.assertEquals(20, discount);
    }

    @Test
    public void testVeggieDiscount_QuantityEqualTo3() {
        Veggie veggie = new Veggie("Carrot", 8000, 15, true);
        int discount = veggie.checkDiscount(3);
        Assertions.assertEquals(10, discount);
    }

    @Test
    public void testVeggieDiscount_QuantityLessThan3() {
        Veggie veggie = new Veggie("Carrot", 8000, 15, true);
        int discount = veggie.checkDiscount(2);
        Assertions.assertEquals(0, discount);
    }
}

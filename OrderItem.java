public class OrderItem {
    private Product product;
    private int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        // TODO: Implement this method.
    };

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getFinalPrice() {
        // TODO: Implement this method.
        int discountPercentage = product.checkDiscount(quantity);
        int discountedPrice = product.price * quantity * (100 - discountPercentage) / 100;
        return discountedPrice;
    }
}

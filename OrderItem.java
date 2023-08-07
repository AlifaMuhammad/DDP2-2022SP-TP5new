public class OrderItem {
    private Product product;
    private int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    };

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    /**
     * method untuk menghitung harga final
     * @return
     */
    public int getFinalPrice() {
        int discountPercentage = product.checkDiscount(quantity);
        int discountedPrice = product.price * quantity * (100 - discountPercentage) / 100;
        return discountedPrice;
    }
}

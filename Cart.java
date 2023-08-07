import java.util.ArrayList;

public class Cart {
    private Customer customer;
    private ArrayList<OrderItem> orderList;

    Cart(Customer customer) {
        this.customer = customer;
        this.orderList = new ArrayList<>();
    }

    public Customer getCustomerName(){
        return customer;
    }

    public ArrayList<OrderItem> getOrderList() {
        return orderList;
    }

    /**
     * method untuk menghitung total harga
     * @return
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderList) {
            totalPrice += orderItem.getFinalPrice();
        }
        if (customer.isPremium && totalPrice > 300_000) {
            totalPrice = (int) (totalPrice * 0.9);
        }
        return totalPrice;
    }

    /**
     * method untuk menambah orderan
     * @param orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        orderList.add(orderItem);
    }
}

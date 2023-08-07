public class Customer {
    String name;
    boolean isPremium;

    Customer(String name, boolean isPremium) {
        this.name = name;
        this.isPremium = isPremium;
    }

    public String getName(){
        return name;
    }
}

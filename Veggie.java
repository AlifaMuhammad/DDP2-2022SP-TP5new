public class Veggie extends Product{
    private boolean isOrganic;

    Veggie(String name, int price, int stock, boolean isOrganic) {
        super(name, price, stock);
        this.isOrganic = isOrganic;
    }


    @Override
    public int checkDiscount(int quantity) {
        if (isOrganic) {
            if (quantity >= 5) {
                return 20;
            } else if (quantity >= 3) {
                return 10;
            }
        } else {
            if (quantity >= 5) {
                return 25;
            } else if (quantity >= 3) {
                return 20;
            }
        }
        return 0;
    }


    public boolean isOrganic() {
        return isOrganic;
    }
}

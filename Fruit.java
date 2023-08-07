public class Fruit extends Product{
    private boolean isLocal;

    Fruit(String name, int price, int stock, boolean isLocal) {
        super(name, price, stock);
        this.isLocal = isLocal;
    }

    @Override
    /**
     * method untuk menghitung diskon yang diperoleh
     */
    public int checkDiscount(int quantity) {
        if (isLocal) {
            if (quantity >= 5) {
                return 30;
            } else if (quantity >= 3) {
                return 20;
            }
        } else {
            if (quantity >= 5) {
                return 20;
            } else if (quantity >= 3) {
                return 15;
            }
        }
        return 0;
    }

    public boolean isLocal() {
        return isLocal;
    }
}

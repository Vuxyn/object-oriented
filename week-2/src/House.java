public class House {
    private int type;
    private double price;

    public House(int type, double price) {
        this.type = type;
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Menghitung PBB berdasarkan type rumah:
     * type 36 -> 4% dari price
     * type 45 -> 6% dari price
     * type above 45 -> 9% dari price
     */
    public double calculatePBB() {
        if (type == 36) {
            return 0.04 * price;
        } else if (type == 45) {
            return 0.06 * price;
        } else if (type > 45) {
            return 0.09 * price;
        } else {
            return 0;
        }
    }
}

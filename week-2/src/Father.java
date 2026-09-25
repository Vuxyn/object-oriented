public class Father {
    private String name;
    private House house;

    public Father(String name, House house) {
        this.name = name;
        this.house = house;
    }

    public Father(String name) {
        this.name = name;
        this.house = null;
    }

    public String getName() {
        return name;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void displayPBB() {
        if (house == null) {
            System.out.println("Father " + name + " tidak memiliki rumah. PBB = 0");
        } else {
            double pbb = house.calculatePBB();
            String priceStr = RupiahFormatter.format(house.getPrice());
            String pbbStr = RupiahFormatter.format(pbb);
            System.out.println("Father " + name + " | House type " + house.getType() + " | Price Rp" + priceStr + " | PBB Rp" + pbbStr);
        }
    }
}

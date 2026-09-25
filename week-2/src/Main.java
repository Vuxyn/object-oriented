public class Main {
    public static void main(String[] args) {

        House houseAkbar = new House(36, 300_000_000); 
        House houseBambang = new House(45, 500_000_000); 
        House houseCharlie = new House(90, 1_000_000_000); 

        Father akbar = new Father("Akbar", houseAkbar);
        Father bambang = new Father("Bambang", houseBambang);
        Father charlie = new Father("Charlie", houseCharlie);

        System.out.println("=== Perhitungan PBB ===");
        akbar.displayPBB();
        bambang.displayPBB();
        charlie.displayPBB();

        System.out.println("");
        System.out.println("=== Detail Perhitungan ===");
        System.out.println("Akbar   : type " + houseAkbar.getType() + " -> 4% x Rp" + RupiahFormatter.format(houseAkbar.getPrice()) + " = Rp" + RupiahFormatter.format(houseAkbar.calculatePBB()));
        System.out.println("Bambang : type " + houseBambang.getType() + " -> 6% x Rp" + RupiahFormatter.format(houseBambang.getPrice()) + " = Rp" + RupiahFormatter.format(houseBambang.calculatePBB()));
        System.out.println("Charlie : type " + houseCharlie.getType() + " -> 9% x Rp" + RupiahFormatter.format(houseCharlie.getPrice()) + " = Rp" + RupiahFormatter.format(houseCharlie.calculatePBB()));
    }
}

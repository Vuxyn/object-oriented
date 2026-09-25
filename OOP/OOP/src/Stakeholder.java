public class Stakeholder {
    private String nama;
    private String id;
    private int umur;
    
    public Stakeholder() {
        this.nama = "";
        this.id = "";
        this.umur = 0;
    }

    public Stakeholder(String nama, String id, int umur) {
        this.nama = nama;
        this.id = id;
        this.umur = umur;
    }

    public String getNama() { return nama; }
    public String getId() { return id; }
    public int getUmur() { return umur; }
    
    public void setNama(String nama) { this.nama = nama; }
    public void setId(String id) { this.id = id; }

    public void setUmur(int umur) {
        if (umur >= 0) {
            this.umur = umur;
        }
    }

    public void displayInfo() {
        System.out.println("Nama: " + nama + ", ID: " + id + ", Umur: " + umur);
    }

    @Override
    public String toString() {
        return "Stakeholder {nama='" + nama + "', id='" + id + "', umur=" + umur + "}";
    }
}

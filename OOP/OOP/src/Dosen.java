public class Dosen extends Stakeholder {

    private String nidn;
    private String mataKuliah;

    public Dosen() {
        super();
        this.nidn = "";
        this.mataKuliah = "";
    }

    public Dosen(String nama, String nidn, int umur, String mataKuliah) {
        super(nama, nidn, umur);
        this.nidn = nidn;
        this.mataKuliah = mataKuliah;
    }

    public String getNidn() { return nidn; }

    public void setNidn(String nidn) {
        this.nidn = nidn;
        super.setId(nidn);
    }

    public String getMataKuliah() { return mataKuliah; }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    @Override
    public void displayInfo() {
        System.out.println("Dosen - Nama: " + getNama() + ", NIDN: " + nidn
                + ", Umur: " + getUmur() + ", Mata Kuliah: " + mataKuliah);
    }

    @Override
    public String toString() {
        return "Dosen{nama='" + getNama() + "', nidn='" + nidn
                + "', umur=" + getUmur() + ", mataKuliah='" + mataKuliah + "'}";
    }
}

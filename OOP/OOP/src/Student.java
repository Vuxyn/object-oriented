public class Student extends Stakeholder {

    private String kelas;
    private Student next;

    public Student() {
        super();
        this.kelas = "";
        this.next = null;
    }

    public Student(String nama, String nim, int umur, String kelas) {
        super(nama, nim, umur);
        this.kelas = kelas;
        this.next = null;
    }

    public String getKelas() { return kelas; }
    public String getNim() { return super.getId(); }
    public Student getNext() { return next; }
    
    public void setKelas(String kelas) { this.kelas = kelas; }
    public void setNim(String nim) { super.setId(nim); }
    public void setNext(Student next) { this.next = next; }

    @Override
    public void displayInfo() {
        System.out.println("Mahasiswa - Nama: " + getNama() + ", NIM: " + getNim()
                + ", Umur: " + getUmur() + ", Kelas: " + kelas);
    }

    @Override
    public String toString() {
        return "Student{nama='" + getNama() + "', nim='" + getNim()
                + "', umur=" + getUmur() + ", kelas='" + kelas + "'}";
    }
}

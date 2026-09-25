import java.util.ArrayList;

public class Prodi {
    private String namaProdi;
    private Student head;
    private int jumlahMahasiswa;
    private final ArrayList<Dosen> daftarDosen;

    public Prodi() {
        this.namaProdi = "";
        this.head = null;
        this.jumlahMahasiswa = 0;
        this.daftarDosen = new ArrayList<>();
    }

    public Prodi(String namaProdi) {
        this.namaProdi = namaProdi;
        this.head = null;
        this.jumlahMahasiswa = 0;
        this.daftarDosen = new ArrayList<>();
    }

    public String getNamaProdi() { return namaProdi; }
    public void setNamaProdi(String namaProdi) { this.namaProdi = namaProdi; }
    public int getJumlahMahasiswa() { return jumlahMahasiswa; }

    public void addStudent(Student newStudent) {
        if (newStudent == null) return;
        newStudent.setNext(null);
        if (head == null) {
            head = newStudent;
        } else {
            Student curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newStudent);
        }
        jumlahMahasiswa++;
    }

    public void addStudent(String nama, String nim, int umur, String kelas) {
        Student s = new Student(nama, nim, umur, kelas);
        addStudent(s);
    }

    public Student findByNim(String nim) {
        Student curr = head;
        while (curr != null) {
            if (curr.getNim().equals(nim)) {
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

    public boolean removeByNim(String nim) {
        if (head == null) return false;
        if (head.getNim().equals(nim)) {
            head = head.getNext();
            jumlahMahasiswa--;
            return true;
        }
        Student curr = head;
        while (curr.getNext() != null) {
            if (curr.getNext().getNim().equals(nim)) {
                curr.setNext(curr.getNext().getNext());
                jumlahMahasiswa--;
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    public void displayStudents() {
        System.out.println("Prodi: " + namaProdi + " | Jumlah Mahasiswa: " + jumlahMahasiswa);
        if (head == null) {
            System.out.println("  (belum ada mahasiswa)");
            return;
        }
        Student curr = head;
        int i = 1;
        while (curr != null) {
            System.out.println("  " + i + ". " + curr);
            curr = curr.getNext();
            i++;
        }
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        Student curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.getNext();
        }
        return list;
    }

    public void addDosen(Dosen dosen) {
        if (dosen != null) {
            daftarDosen.add(dosen);
        }
    }

    public void addDosen(String nama, String nidn, int umur, String mataKuliah) {
        daftarDosen.add(new Dosen(nama, nidn, umur, mataKuliah));
    }

    public void displayDosen() {
        System.out.println("Daftar Dosen Prodi " + namaProdi + ":");
        if (daftarDosen.isEmpty()) {
            System.out.println("  (belum ada dosen)");
            return;
        }
        for (int i = 0; i < daftarDosen.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + daftarDosen.get(i));
        }
    }

    public ArrayList<Dosen> getDaftarDosen() {
        return daftarDosen;
    }

    @Override
    public String toString() {
        return "Prodi{namaProdi='" + namaProdi + "', jumlahMahasiswa=" + jumlahMahasiswa
                + ", jumlahDosen=" + daftarDosen.size() + "}";
    }
}

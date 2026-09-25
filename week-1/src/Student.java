public class Student {
    String name;
    String nim;
    int age;

    public Student(String name, String nim, int age){
        this.name = name;
        this.nim = nim;
        this.age = age;
    }
    
    void printInfo() {
        System.out.println("Nama: " + name + ", NIM: " + nim + ", Umur: " + age);
    }
}

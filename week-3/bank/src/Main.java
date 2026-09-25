import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Bank bank = new Bank("Bank OOP");

    public static void main(String[] args) {
        seedData();

        while (true) {
            printMenu();
            int pilih = readInt("Pilih menu: ");
            switch (pilih) {
                case 1 -> tambahCustomer();
                case 2 -> lihatSemuaCustomer();
                case 3 -> buatAkun();
                case 4 -> deposit();
                case 5 -> withdraw();
                case 6 -> detailCustomer();
                case 0 -> {
                    System.out.println("Terima kasih! Sampai jumpa.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.\n");
            }
        }
    }

    private static void seedData() {
        Customer mahesa = bank.addCustomer("Mahesa", "Smith");
        mahesa.addAccount(new Account(500.00));
        Customer jay = bank.addCustomer("Jay", "Bryant");
        jay.addAccount(new Account(200.00));
    }

    private static void printMenu() {
        System.out.println("===== " + bank.getBankName() + " =====");
        System.out.println("Nasabah: " + bank.getNumOfCustomers());
        System.out.println("1. Tambah Customer");
        System.out.println("2. Lihat Semua Customer");
        System.out.println("3. Buat Akun Baru untuk Customer");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Detail Customer");
        System.out.println("0. Keluar");
    }

    private static void tambahCustomer() {
        System.out.print("Nama depan: ");
        String first = in.nextLine().trim();
        System.out.print("Nama belakang: ");
        String last = in.nextLine().trim();
        if (first.isEmpty() || last.isEmpty()) {
            System.out.println("Gagal: nama tidak boleh kosong.\n");
            return;
        }
        Customer cust = bank.addCustomer(first, last);
        System.out.println("Customer ditambahkan: " + cust.getFullName() + "\n");
    }

    private static void lihatSemuaCustomer() {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada nasabah.\n");
            return;
        }
        System.out.println("--- Daftar Nasabah ---");
        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            System.out.println("[" + i + "] " + bank.getCustomer(i));
        }
        System.out.println();
    }

    private static void buatAkun() {
        int ci = pilihCustomer();
        if (ci < 0) return;
        double init = readDouble("Saldo awal: ");
        if (init < 0) {
            System.out.println("Gagal: saldo awal tidak boleh negatif.\n");
            return;
        }
        Account acc = new Account(init);
        bank.getCustomer(ci).addAccount(acc);
        System.out.println("Akun dibuat: " + acc + "\n");
    }

    private static void deposit() {
        Account acc = pilihAccount();
        if (acc == null) return;
        double amt = readDouble("Nominal deposit: ");
        if (acc.deposit(amt)) {
            System.out.println("Berhasil. Saldo sekarang: " + acc.getBalance() + "\n");
        } else {
            System.out.println("Gagal. Nominal harus > 0.\n");
        }
    }

    private static void withdraw() {
        Account acc = pilihAccount();
        if (acc == null) return;
        double amt = readDouble("Nominal withdraw: ");
        if (acc.withdraw(amt)) {
            System.out.println("Berhasil. Saldo sekarang: " + acc.getBalance() + "\n");
        } else {
            System.out.println("Gagal. Nominal harus > 0 dan <= saldo.\n");
        }
    }

    private static void detailCustomer() {
        int ci = pilihCustomer();
        if (ci < 0) return;
        Customer c = bank.getCustomer(ci);
        System.out.println("--- " + c.getFullName() + " ---");
        if (c.getNumOfAccounts() == 0) {
            System.out.println("Belum punya akun.\n");
            return;
        }
        for (int i = 0; i < c.getNumOfAccounts(); i++) {
            System.out.println("  [" + i + "] " + c.getAccount(i));
        }
        System.out.println("  Total: " + String.format("%.2f", c.getTotalBalance()) + "\n");
    }

    private static int pilihCustomer() {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada nasabah.\n");
            return -1;
        }
        lihatSemuaCustomer();
        int idx = readInt("Pilih index customer: ");
        if (idx < 0 || idx >= bank.getNumOfCustomers()) {
            System.out.println("Index tidak valid.\n");
            return -1;
        }
        return idx;
    }

    private static Account pilihAccount() {
        int ci = pilihCustomer();
        if (ci < 0) return null;
        Customer c = bank.getCustomer(ci);
        if (c.getNumOfAccounts() == 0) {
            System.out.println("Customer belum punya akun.\n");
            return null;
        }
        for (int i = 0; i < c.getNumOfAccounts(); i++) {
            System.out.println("  [" + i + "] " + c.getAccount(i));
        }
        int ai = readInt("Pilih index akun: ");
        if (ai < 0 || ai >= c.getNumOfAccounts()) {
            System.out.println("Index akun tidak valid.\n");
            return null;
        }
        return c.getAccount(ai);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan nominal yang valid.");
            }
        }
    }
}

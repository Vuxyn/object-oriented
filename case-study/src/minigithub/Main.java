package minigithub;

import minigithub.contract.Closable;
import minigithub.contract.Reviewable;
import minigithub.exception.ValidationException;
import minigithub.model.BugReport;
import minigithub.model.Commit;
import minigithub.model.Contribution;
import minigithub.model.PullRequest;
import minigithub.model.User;
import minigithub.repository.PrivateRepository;
import minigithub.repository.PublicRepository;
import minigithub.repository.Repository;

import java.util.List;

/**
 * Demo singkat 5 konsep OOP:
 * 1. Abstract class + abstract method
 * 2. Interface
 * 3. Inheritance
 * 4. Polymorphism
 * 5. Encapsulation
 */
public class Main {

    public static void main(String[] args) {
        User andi = new User("andi");
        User budi = new User("budi");
        User citra = new User("citra");

        // =================================================================
        // 1. ABSTRACT CLASS + POLYMORPHISM
        // =================================================================
        section("1. ABSTRACT CLASS + POLYMORPHISM");

        Repository publicRepo = new PublicRepository("mini-github", andi);
        Repository privateRepo = new PrivateRepository("catatan-rahasia", andi);
        privateRepo.addCollaborator(budi);

        // Variabel bertipe Repository, isinya objek subclass berbeda.
        for (Repository repo : List.of(publicRepo, privateRepo)) {
            System.out.println(repo.getInfo());
            System.out.println("  budi  bisa lihat? " + yesNo(repo.canView(budi)));
            System.out.println("  citra bisa lihat? " + yesNo(repo.canView(citra)));
        }

        Commit commit = new Commit(andi, "Tambah class Repository", 3);
        BugReport bug = new BugReport(citra, "Poin PR tidak bertambah", 3);
        PullRequest pr = new PullRequest(budi, "Implementasi review", "feature/review", "main");

        publicRepo.addContribution(commit);
        publicRepo.addContribution(bug);
        publicRepo.addContribution(pr);

        System.out.println();
        printFeed(publicRepo);

        // =================================================================
        // 2. INTERFACE Reviewable
        // =================================================================
        section("2. INTERFACE Reviewable");

        Reviewable reviewable = pr; // PR implements Reviewable
        try {
            reviewable.review(budi); // budi adalah author PR
        } catch (ValidationException e) {
            System.out.println("Ditolak : " + e.getMessage());
        }

        reviewable.review(andi);
        System.out.println("andi me-review PR #" + pr.getId()
                + ", approval = " + pr.getApprovalCount());

        pr.merge();
        System.out.println("PR #" + pr.getId() + " berhasil di-merge");

        // =================================================================
        // 3. INTERFACE Closable
        // =================================================================
        section("3. INTERFACE Closable");

        Closable closable = bug; // BugReport mewarisi Closable dari Issue
        closable.close();
        System.out.println("BugReport ditutup? " + closable.isClosed());

        Closable closedPr = pr; // PR implements Closable
        try {
            closedPr.close(); // sudah di-merge, jadi ditolak
        } catch (IllegalStateException e) {
            System.out.println("Gagal   : " + e.getMessage());
        }

        // Commit tidak implements Closable, jadi tidak punya method close().
        System.out.println("Commit tidak bisa ditutup karena bukan Closable");

        // =================================================================
        // 4. VALIDASI
        // =================================================================
        section("4. VALIDASI");

        try {
            new Commit(andi, "   ", 1);
        } catch (ValidationException e) {
            System.out.println("Ditolak : " + e.getMessage());
        }

        try {
            new BugReport(andi, "Bug aneh", 9);
        } catch (ValidationException e) {
            System.out.println("Ditolak : " + e.getMessage());
        }

        // =================================================================
        // 5. HASIL AKHIR
        // =================================================================
        section("5. HASIL AKHIR");
        printFeed(publicRepo);
    }

    private static void printFeed(Repository repository) {
        System.out.println(repository.getInfo());
        for (Contribution contribution : repository.getContributions()) {
            // Tipe variabelnya Contribution, tetapi describe() memakai getPoints()
            // dan getSummary() versi subclass masing-masing (polymorphism).
            System.out.println("  " + contribution.describe());
        }
        System.out.println("  Total poin: " + repository.getTotalPoints());
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    private static String yesNo(boolean value) {
        return value ? "ya" : "tidak";
    }
}

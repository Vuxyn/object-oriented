package minigithub.model;

import minigithub.validation.Validator;

import java.time.LocalDate;

/**
 * ABSTRACT CLASS + ABSTRACT METHOD
 *
 * Contribution adalah konsep umum: tidak bisa dibuat langsung dengan
 * "new Contribution(...)". Yang benar-benar dibuat adalah Commit, Issue,
 * atau PullRequest.
 *
 * Dua abstract method di bawah wajib di-override subclass karena setiap jenis
 * kontribusi punya ringkasan dan aturan poin yang berbeda.
 *
 * INHERITANCE: semua subclass mewarisi field id, author, createdAt, title dan
 * method konkret seperti getId(), getType(), dan describe().
 */
public abstract class Contribution {

    private static int idCounter = 0;

    private final int id;
    private final User author;
    private final LocalDate createdAt;
    private final String title;

    /** protected = hanya bisa dipanggil subclass lewat super(...). */
    protected Contribution(User author, String title) {
        this.id = ++idCounter;
        this.author = Validator.requireNotNull(author, "Author");
        this.createdAt = LocalDate.now();
        this.title = Validator.requireText(title, "Judul");
    }

    /** ABSTRACT METHOD: wajib diisi subclass. */
    public abstract String getSummary();

    /** ABSTRACT METHOD: poin berbeda-beda per jenis kontribusi. */
    public abstract int getPoints();

    /**
     * Method konkret yang diwariskan.
     *
     * POLYMORPHISM: getPoints() dan getSummary() dipanggil lewat tipe
     * Contribution, tetapi versi yang berjalan adalah milik objek aslinya
     * (Commit, BugReport, PullRequest, dst).
     */
    public final String describe() {
        return String.format("#%d %-12s %-8s %2d poin | %s",
                id, getType(), author, getPoints(), getSummary());
    }

    /** Nama class asli, otomatis menyesuaikan objeknya. */
    public String getType() {
        return getClass().getSimpleName();
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }
}

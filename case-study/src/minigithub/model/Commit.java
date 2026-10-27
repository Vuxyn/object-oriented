package minigithub.model;

import minigithub.validation.Validator;

/**
 * INHERITANCE (level 1): Commit extends Contribution.
 *
 * Commit "is-a" Contribution, jadi ia mewarisi semua field dan method umum
 * dari Contribution tanpa menulis ulang.
 *
 * Commit TIDAK implements Closable/Reviewable karena commit memang tidak bisa
 * ditutup atau di-review. Inilah contoh mengapa "kemampuan" lebih tepat
 * dibuat sebagai interface, bukan ditumpuk di abstract class.
 */
public class Commit extends Contribution {

    private final int filesChanged;

    public Commit(User author, String message, int filesChanged) {
        super(author, message); // memanggil constructor induk
        Validator.requireMin(filesChanged, 1, "Commit harus mengubah minimal 1 file");
        this.filesChanged = filesChanged;
    }

    @Override
    public String getSummary() {
        return "commit \"" + getTitle() + "\" (" + filesChanged + " file)";
    }

    @Override
    public int getPoints() {
        return filesChanged >= 5 ? 2 : 1; // commit besar dapat bonus 1 poin
    }
}

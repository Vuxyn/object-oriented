package minigithub.repository;

import minigithub.model.Contribution;
import minigithub.model.User;
import minigithub.validation.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * ABSTRACT CLASS + ABSTRACT METHOD
 *
 * Repository tidak bisa dibuat langsung. Yang dibuat adalah PublicRepository
 * atau PrivateRepository. canView() dibuat abstract karena aturan akses tiap
 * jenis repository berbeda.
 *
 * ENCAPSULATION: semua field private. Daftar kontribusi hanya bisa diubah
 * lewat addContribution(), dan getContributions() mengembalikan salinan
 * read-only supaya tidak bisa diubah dari luar.
 */
public abstract class Repository {

    private final String name;
    private final User owner;
    private final String visibility;
    private final List<User> collaborators = new ArrayList<>();
    private final List<Contribution> contributions = new ArrayList<>();

    protected Repository(String name, User owner, String visibility) {
        this.name = Validator.requireText(name, "Nama repository");
        this.owner = Validator.requireNotNull(owner, "Owner");
        this.visibility = visibility;
    }

    /** ABSTRACT METHOD: wajib di-override subclass. */
    public abstract boolean canView(User user);

    public void addContribution(Contribution contribution) {
        contributions.add(Validator.requireNotNull(contribution, "Kontribusi"));
    }

    /**
     * POLYMORPHISM: getPoints() dipanggil lewat tipe Contribution, tetapi yang
     * dijalankan adalah versi milik masing-masing subclass.
     */
    public int getTotalPoints() {
        int total = 0;
        for (Contribution contribution : contributions) {
            total += contribution.getPoints();
        }
        return total;
    }

    /** List.copyOf membuat salinan yang tidak bisa diubah dari luar. */
    public List<Contribution> getContributions() {
        return List.copyOf(contributions);
    }

    public void addCollaborator(User user) {
        Validator.requireNotNull(user, "Collaborator");
        if (!collaborators.contains(user)) {
            collaborators.add(user);
        }
    }

    public boolean isMember(User user) {
        return user != null && (owner.equals(user) || collaborators.contains(user));
    }

    public String getInfo() {
        return owner.getUsername() + "/" + name + " [" + visibility + "] - "
                + contributions.size() + " kontribusi";
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}

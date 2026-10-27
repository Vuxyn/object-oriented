package minigithub.repository;

import minigithub.model.User;

/**
 * INHERITANCE: PrivateRepository extends Repository.
 *
 * canView() di-override dengan aturan berbeda dari PublicRepository.
 * Pemanggilan method sama, hasil bisa beda -> POLYMORPHISM.
 */
public class PrivateRepository extends Repository {

    public PrivateRepository(String name, User owner) {
        super(name, owner, "Private");
    }

    @Override
    public boolean canView(User user) {
        return isMember(user); // hanya owner dan collaborator
    }
}

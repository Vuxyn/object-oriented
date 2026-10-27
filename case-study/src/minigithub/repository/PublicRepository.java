package minigithub.repository;

import minigithub.model.User;

/**
 * INHERITANCE: PublicRepository extends Repository.
 * Subclass hanya perlu mengisi abstract method canView().
 */
public class PublicRepository extends Repository {

    public PublicRepository(String name, User owner) {
        super(name, owner, "Public");
    }

    @Override
    public boolean canView(User user) {
        return true; // repository public boleh dilihat siapa saja
    }
}

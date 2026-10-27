package minigithub.model;

import minigithub.validation.Validator;

/**
 * ENCAPSULATION: username private + final, jadi tidak bisa diubah setelah
 * objek dibuat. Akses hanya lewat getter.
 *
 * equals() dan hashCode() membuat dua User dengan username sama dianggap
 * orang yang sama. Ini dipakai saat mengecek apakah reviewer adalah author PR.
 */
public class User {

    private final String username;

    public User(String username) {
        this.username = Validator.requireText(username, "Username");
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equalsIgnoreCase(user.username);
    }

    @Override
    public int hashCode() {
        return username.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "@" + username;
    }
}

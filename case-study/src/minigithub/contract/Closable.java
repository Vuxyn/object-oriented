package minigithub.contract;

/**
 * INTERFACE: kontrak kemampuan "bisa ditutup".
 *
 * Kenapa interface, bukan abstract class?
 * Hanya sebagian Contribution yang bisa ditutup (Issue dan PullRequest).
 * Commit tidak implements Closable, sehingga tidak dipaksa punya method close()
 * yang tidak masuk akal.
 */
public interface Closable {

    /** Menutup objek. Boleh melempar IllegalStateException jika status tidak valid. */
    void close();

    boolean isClosed();
}

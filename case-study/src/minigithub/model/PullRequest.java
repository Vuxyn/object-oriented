package minigithub.model;

import minigithub.contract.Closable;
import minigithub.contract.Reviewable;
import minigithub.validation.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * INHERITANCE: PullRequest extends Contribution.
 * INTERFACE: implements Closable dan Reviewable sekaligus.
 *
 * Java hanya mengizinkan satu extends, tetapi boleh banyak implements.
 * Inilah alasan interface dipakai untuk "kemampuan" yang bisa digabung.
 *
 * ENCAPSULATION: status dan approvers private. Status hanya bisa berubah
 * lewat close(), review(), dan merge() yang punya aturan validasi.
 */
public class PullRequest extends Contribution implements Closable, Reviewable {

    public enum Status { OPEN, CLOSED, MERGED }

    private final String sourceBranch;
    private final String targetBranch;
    private final List<User> approvers = new ArrayList<>();
    private Status status = Status.OPEN;

    public PullRequest(User author, String title, String sourceBranch, String targetBranch) {
        super(author, title);
        this.sourceBranch = Validator.requireText(sourceBranch, "Branch asal");
        this.targetBranch = Validator.requireText(targetBranch, "Branch tujuan");
        Validator.requireTrue(!this.sourceBranch.equals(this.targetBranch),
                "Branch asal dan tujuan tidak boleh sama");
    }

    // ===== implementasi Closable =====

    @Override
    public void close() {
        if (status == Status.MERGED) {
            throw new IllegalStateException("PR #" + getId() + " sudah di-merge, tidak bisa ditutup");
        }
        status = Status.CLOSED;
    }

    @Override
    public boolean isClosed() {
        return status == Status.CLOSED;
    }

    // ===== implementasi Reviewable =====

    @Override
    public void review(User reviewer) {
        Validator.requireNotNull(reviewer, "Reviewer");
        if (status != Status.OPEN) {
            throw new IllegalStateException("PR #" + getId() + " tidak terbuka untuk di-review");
        }
        Validator.requireTrue(!reviewer.equals(getAuthor()),
                "Author tidak boleh me-review PR sendiri");
        if (!approvers.contains(reviewer)) {
            approvers.add(reviewer);
        }
    }

    @Override
    public int getApprovalCount() {
        return approvers.size();
    }

    // ===== method khusus PullRequest =====

    /** merge() bukan abstract method; hanya PullRequest yang punya aksi ini. */
    public void merge() {
        if (status != Status.OPEN) {
            throw new IllegalStateException("PR #" + getId() + " berstatus " + status + ", tidak bisa di-merge");
        }
        if (approvers.isEmpty()) {
            throw new IllegalStateException("PR #" + getId() + " butuh minimal 1 approval");
        }
        status = Status.MERGED;
    }

    @Override
    public String getSummary() {
        return "PR " + sourceBranch + " -> " + targetBranch
                + " [" + status + ", " + approvers.size() + " approval]";
    }

    @Override
    public int getPoints() {
        return status == Status.MERGED ? 8 : 5; // 5 poin dasar + 3 bonus merge
    }

    public Status getStatus() {
        return status;
    }

    public String getSourceBranch() {
        return sourceBranch;
    }

    public String getTargetBranch() {
        return targetBranch;
    }
}

package minigithub.contract;

import minigithub.model.User;

/**
 * INTERFACE: kontrak kemampuan "bisa di-review".
 * Hanya PullRequest yang implements interface ini.
 */
public interface Reviewable {

    /** Memberi approval dari reviewer. */
    void review(User reviewer);

    int getApprovalCount();
}

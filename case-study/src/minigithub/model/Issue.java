package minigithub.model;

import minigithub.contract.Closable;

/**
 * INHERITANCE (level 1): Issue extends Contribution.
 * INTERFACE: Issue implements Closable, artinya Issue punya kemampuan close().
 *
 * Issue juga menjadi induk dari BugReport, sehingga terbentuk inheritance
 * bertingkat: Contribution -> Issue -> BugReport.
 */
public class Issue extends Contribution implements Closable {

    private boolean closed = false;

    public Issue(User author, String title) {
        super(author, title);
    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public String getSummary() {
        return "issue \"" + getTitle() + "\" [" + (closed ? "CLOSED" : "OPEN") + "]";
    }

    @Override
    public int getPoints() {
        return 3;
    }
}

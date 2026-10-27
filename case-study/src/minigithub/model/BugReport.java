package minigithub.model;

import minigithub.validation.Validator;

/**
 * INHERITANCE BERTINGKAT (level 2): BugReport extends Issue extends Contribution.
 *
 * close() dan isClosed() TIDAK ditulis ulang karena sudah diwarisi dari Issue.
 * BugReport hanya menambah field severity dan meng-override method yang
 * perilakunya memang berbeda.
 */
public class BugReport extends Issue {

    private final int severity; // 1 = ringan, 3 = kritis

    public BugReport(User author, String title, int severity) {
        super(author, title);
        Validator.requireRange(severity, 1, 3, "Severity harus antara 1 sampai 3");
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }

    @Override
    public String getSummary() {
        // super.getSummary() memakai logika Issue, lalu ditambah info severity.
        return "[BUG sev-" + severity + "] " + super.getSummary();
    }

    @Override
    public int getPoints() {
        return super.getPoints() + severity; // 3 poin issue + severity
    }
}

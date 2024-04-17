package net.bodz.bas.vcs;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.time.DateTimes;

public class MutableVcsLogEntry
        implements
            IMutableVcsLogEntry {

    private String version;
    private String treeHash;

    private String authorName;
    private String authorEmail;
    private ZonedDateTime authorDate;

    private String committerName;
    private String committerEmail;
    private ZonedDateTime commitDate;

    private String subject;
    private String body;

    private List<IVcsLogEntry> parents;
    private List<IFileChangement> changes;

    public MutableVcsLogEntry() {
        parents = new ArrayList<IVcsLogEntry>();
        changes = new ArrayList<IFileChangement>();
    }

    public MutableVcsLogEntry(String version, String subject) {
        this();
        if (version == null)
            throw new NullPointerException("version");
        if (subject == null)
            throw new NullPointerException("subject");
        this.version = version;
        this.subject = subject;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getTreeHash() {
        return treeHash;
    }

    @Override
    public void setTreeHash(String treeHash) {
        this.treeHash = treeHash;
    }

    @Override
    public String getAuthorName() {
        return authorName;
    }

    @Override
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String getAuthorEmail() {
        return authorEmail;
    }

    @Override
    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @Override
    public ZonedDateTime getAuthorDate() {
        return authorDate;
    }

    @Override
    public void setAuthorDate(ZonedDateTime authorDate) {
        this.authorDate = authorDate;
    }

    @Override
    public String getCommitterName() {
        if (committerName == null)
            return authorName;
        else
            return committerName;
    }

    @Override
    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    @Override
    public String getCommitterEmail() {
        if (committerName == null && committerEmail == null)
            return authorEmail;
        else
            return committerEmail;
    }

    @Override
    public void setCommitterEmail(String committerEmail) {
        this.committerEmail = committerEmail;
    }

    @Override
    public ZonedDateTime getCommitDate() {
        return commitDate;
    }

    @Override
    public void setCommitDate(ZonedDateTime commitDate) {
        this.commitDate = commitDate;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public List<IVcsLogEntry> getParents() {
        return parents;
    }

    @Override
    public List<IFileChangement> getChanges() {
        return changes;
    }

    public void setChanges(List<IFileChangement> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(version.substring(0, 6));
        buf.append(" [");
        buf.append(authorName);
        buf.append("] ");
        buf.append(subject);
        buf.append(" (");
        buf.append(DateTimes.ISO_ZONED_DATE_TIME.format(authorDate));
        buf.append(")");
        return buf.toString();
    }

}

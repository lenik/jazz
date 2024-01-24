package net.bodz.violet.schema.issue;

import net.bodz.lily.concrete.VoteRecord;

public class IssueLogVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    IssueLog log;

    public IssueLogVote() {
    }

    public IssueLog getLog() {
        return log;
    }

    public void setLog(IssueLog log) {
        this.log = log;
    }

}

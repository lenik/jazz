package net.bodz.violet.issue;

import net.bodz.lily.template.VoteRecord;

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

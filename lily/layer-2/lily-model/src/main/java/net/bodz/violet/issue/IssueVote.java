package net.bodz.violet.issue;

import net.bodz.lily.template.VoteRecord;

public class IssueVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    Issue issue;

    public IssueVote() {
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

}

package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
@Table(name = "issuelog")
public class IssueLog
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    private Issue issue;

    public IssueLog() {
        super();
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

}

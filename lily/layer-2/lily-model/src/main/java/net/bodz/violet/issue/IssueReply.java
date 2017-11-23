package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

@IdType(Long.class)
@Table(name = "issuelog")
public class IssueReply
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    private Issue issue;

    public IssueReply() {
        super();
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

}

package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.template.CoPhase;

@Table(name = "issuephase")
public class IssuePhase
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public IssuePhase() {
        super();
    }

    public IssuePhase(IssuePhase parent) {
        super(parent);
    }

}

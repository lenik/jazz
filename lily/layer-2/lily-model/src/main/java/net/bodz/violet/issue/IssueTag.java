package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.model.base.CoCode;

@Table(name = "issuetag")
public class IssueTag
        extends CoCode<IssueTag> {

    private static final long serialVersionUID = 1L;

    public IssueTag() {
        super();
    }

    public IssueTag(IssueTag parent) {
        super(parent);
    }

}

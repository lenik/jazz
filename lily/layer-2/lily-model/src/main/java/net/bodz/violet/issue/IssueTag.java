package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

@Table(name = "issuetag")
public class IssueTag
        extends CoTag<IssueTag> {

    private static final long serialVersionUID = 1L;

    public IssueTag() {
        super();
    }

    public IssueTag(IssueTag parent) {
        super(parent);
    }

}

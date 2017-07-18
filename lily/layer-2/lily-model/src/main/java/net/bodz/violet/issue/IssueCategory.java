package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

@IdType(Integer.class)
@Table(name = "issuecat")
public class IssueCategory
        extends CoNode<IssueCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public IssueCategory() {
        super();
    }

    public IssueCategory(IssueCategory parent) {
        super(parent);
    }

}

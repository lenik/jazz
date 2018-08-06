package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

@IdType(Integer.class)
@Table(name = "issuecat")
public class IssueCategory
        extends CoCategory<IssueCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public IssueCategory() {
        super();
    }

    public IssueCategory(IssueCategory parent) {
        super(parent);
    }

}

package net.bodz.violet.issue;

import javax.persistence.Table;

import net.bodz.lily.template.FavRecord;

@Table(name = "issue_fav")
public class IssueFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    Issue issue;

    public IssueFav() {
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

}

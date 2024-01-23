package net.bodz.violet.issue;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Long.class)
public abstract class _IssueFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "issue_fav";

    public static final String FIELD_ISSUE_ID = "issue";

    public static final int N_ISSUE_ID = 19;

    private static final int _ord_ISSUE_ID = 2;

    /**  */
    @NotNull
    Issue issue;

    @NotNull
    long issueId;

    /**
     *
     * @label issue
     * @constraint foreign key (issue) references violet.issue (id)
     */
    @NotNull
    public Issue getIssue() {
        return issue;
    }

    /**
     */
    public void setIssue(@NotNull Issue value) {
        this.issue = value;
    }

    @Ordinal(_ord_ISSUE_ID)
    @Precision(value = 19)
    @Column(name = "issue", nullable = false, precision = 19)
    public synchronized long getIssueId() {
        if (issue != null) {
            if (issue.getId() == null)
                return 0L;
            return issue.getId();
        }
        return issueId;
    }

    public synchronized void setIssueId(long value) {
        this.issueId = value;
    }

    public void initNotNulls() {
    }

}

package net.bodz.violet.schema.issue;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

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
     * @constraint foreign key (issue) references violet.issue (id)
     */
    @JoinColumn(name = "issue")
    @ManyToOne
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

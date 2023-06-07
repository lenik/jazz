package net.bodz.violet.issue;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.VoteRecord;

@IdType(Long.class)
public abstract class _IssueVote_stuff
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    private static final int _ord_PARENT_ID = 2;
    private static final int _ord_VOTE_SCORE = _ord_PARENT_ID + 2;

    @NotNull
    int voteScore;

    /**  */
    @NotNull
    Issue parent;

    @NotNull
    long parentId;

    @Ordinal(_ord_VOTE_SCORE)
    @Precision(value = 10)
    @Column(name = "votes", nullable = false, precision = 10)
    public int getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(int value) {
        this.voteScore = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references violet.issue (id)
     */
    @NotNull
    public Issue getParent() {
        return parent;
    }

    /**
     */
    public void setParent(@NotNull Issue value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = 19)
    @Column(name = "parent", nullable = false, precision = 19)
    public synchronized long getParentId() {
        if (parent != null) {
            if (parent.getId() == null)
                return 0L;
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(long value) {
        this.parentId = value;
    }

    public void initNotNulls() {
    }

}

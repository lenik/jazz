package net.bodz.violet.edu;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.VoteRecord;

@IdType(Long.class)
public abstract class _TestQuestionVote_stuff
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testq_vote";

    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_VOTE_SCORE = "votes";

    public static final int N_PARENT_ID = 19;
    public static final int N_VOTE_SCORE = 10;

    private static final int _ord_PARENT_ID = 2;
    private static final int _ord_VOTE_SCORE = _ord_PARENT_ID + 2;

    @NotNull
    int voteScore;

    /**  */
    @NotNull
    TestQuestion parent;

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
     * @constraint foreign key (parent) references violet.testq (id)
     */
    @NotNull
    public TestQuestion getParent() {
        return parent;
    }

    /**
     */
    public void setParent(@NotNull TestQuestion value) {
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

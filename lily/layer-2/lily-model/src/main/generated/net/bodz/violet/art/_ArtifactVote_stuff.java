package net.bodz.violet.art;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.VoteRecord;

@IdType(Long.class)
public abstract class _ArtifactVote_stuff
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "art_vote";

    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_VOTE_SCORE = "votes";

    public static final int N_PARENT_ID = 10;
    public static final int N_VOTE_SCORE = 10;

    private static final int _ord_PARENT_ID = 2;
    private static final int _ord_VOTE_SCORE = _ord_PARENT_ID + 2;

    @NotNull
    int voteScore;

    /**  */
    @NotNull
    Artifact parent;

    @NotNull
    int parentId;

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
     * @constraint foreign key (parent) references violet.art (id)
     */
    @NotNull
    public Artifact getParent() {
        return parent;
    }

    /**
     */
    public void setParent(@NotNull Artifact value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = 10)
    @Column(name = "parent", nullable = false, precision = 10)
    public synchronized int getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(int value) {
        this.parentId = value;
    }

    public void initNotNulls() {
    }

}

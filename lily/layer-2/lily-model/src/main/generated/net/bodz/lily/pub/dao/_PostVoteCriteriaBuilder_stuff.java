package net.bodz.lily.pub.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PostVoteCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Long parentId;
    LongRange parentIdRange = new LongRange();

    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

    Integer voteScore;
    IntegerRange voteScoreRange = new IntegerRange();

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public LongRange getIdRange() {
        return idRange;
    }

    public void setIdRange(LongRange range) {
        this.idRange = range;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long value) {
        this.parentId = value;
    }

    public LongRange getParentIdRange() {
        return parentIdRange;
    }

    public void setParentIdRange(LongRange range) {
        this.parentIdRange = range;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public IntegerRange getUserIdRange() {
        return userIdRange;
    }

    public void setUserIdRange(IntegerRange range) {
        this.userIdRange = range;
    }

    public Integer getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(Integer value) {
        this.voteScore = value;
    }

    public IntegerRange getVoteScoreRange() {
        return voteScoreRange;
    }

    public void setVoteScoreRange(IntegerRange range) {
        this.voteScoreRange = range;
    }

}

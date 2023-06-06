package net.bodz.lily.pub.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _ArticleTalkVoteMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Long articleMsgId;
    LongRange articleMsgIdRange = new LongRange();

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

    public Long getArticleMsgId() {
        return articleMsgId;
    }

    public void setArticleMsgId(Long value) {
        this.articleMsgId = value;
    }

    public LongRange getArticleMsgIdRange() {
        return articleMsgIdRange;
    }

    public void setArticleMsgIdRange(LongRange range) {
        this.articleMsgIdRange = range;
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

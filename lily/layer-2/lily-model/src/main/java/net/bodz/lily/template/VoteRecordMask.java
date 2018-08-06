package net.bodz.lily.template;

import net.bodz.lily.model.base.CoObjectMask;

public abstract class VoteRecordMask
        extends CoObjectMask {

    Integer userId;
    Boolean voteUp;
    Boolean voteDown;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(Boolean voteUp) {
        this.voteUp = voteUp;
    }

    public Boolean getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(Boolean voteDown) {
        this.voteDown = voteDown;
    }

}

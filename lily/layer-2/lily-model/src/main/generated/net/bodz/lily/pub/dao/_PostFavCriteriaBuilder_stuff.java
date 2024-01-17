package net.bodz.lily.pub.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PostFavCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Long postId;
    LongRange postIdRange = new LongRange();

    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long value) {
        this.postId = value;
    }

    public LongRange getPostIdRange() {
        return postIdRange;
    }

    public void setPostIdRange(LongRange range) {
        this.postIdRange = range;
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

}

package net.bodz.lily.pub.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _ArticleFavMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Long articleId;
    LongRange articleIdRange = new LongRange();

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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long value) {
        this.articleId = value;
    }

    public LongRange getArticleIdRange() {
        return articleIdRange;
    }

    public void setArticleIdRange(LongRange range) {
        this.articleIdRange = range;
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

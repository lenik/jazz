package net.bodz.violet.edu.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _TestQuestionFavMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Long testqId;
    LongRange testqIdRange = new LongRange();

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

    public Long getTestqId() {
        return testqId;
    }

    public void setTestqId(Long value) {
        this.testqId = value;
    }

    public LongRange getTestqIdRange() {
        return testqIdRange;
    }

    public void setTestqIdRange(LongRange range) {
        this.testqIdRange = range;
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

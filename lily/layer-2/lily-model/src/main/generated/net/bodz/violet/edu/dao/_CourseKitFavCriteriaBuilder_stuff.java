package net.bodz.violet.edu.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _CourseKitFavCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Integer coursekitId;
    IntegerRange coursekitIdRange = new IntegerRange();

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

    public Integer getCoursekitId() {
        return coursekitId;
    }

    public void setCoursekitId(Integer value) {
        this.coursekitId = value;
    }

    public IntegerRange getCoursekitIdRange() {
        return coursekitIdRange;
    }

    public void setCoursekitIdRange(IntegerRange range) {
        this.coursekitIdRange = range;
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

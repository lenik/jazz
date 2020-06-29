package net.bodz.violet.edu.impl;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.Course
 */
public class CourseMask
        extends CoObjectMask {

    Integer categoryId;
    IntegerRange creditRange;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public IntegerRange getCreditRange() {
        return creditRange;
    }

    public void setCreditRange(IntegerRange creditRange) {
        this.creditRange = creditRange;
    }

}

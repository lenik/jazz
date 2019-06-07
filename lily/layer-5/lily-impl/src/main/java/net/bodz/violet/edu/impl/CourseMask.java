package net.bodz.violet.edu.impl;

import net.bodz.bas.t.range.IntRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.Course
 */
public class CourseMask
        extends CoObjectMask {

    Integer categoryId;
    IntRange creditRange;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public IntRange getCreditRange() {
        return creditRange;
    }

    public void setCreditRange(IntRange creditRange) {
        this.creditRange = creditRange;
    }

}

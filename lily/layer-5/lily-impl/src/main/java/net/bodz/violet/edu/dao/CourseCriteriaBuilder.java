package net.bodz.violet.edu.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.edu.Course
 */
public class CourseCriteriaBuilder
        extends CoObjectCriteriaBuilder {

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

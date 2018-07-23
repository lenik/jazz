package net.bodz.violet.edu.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.CourseKit
 */
public class CourseKitMask
        extends CoObjectMask {

    Integer categoryId;
    Integer courseId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}

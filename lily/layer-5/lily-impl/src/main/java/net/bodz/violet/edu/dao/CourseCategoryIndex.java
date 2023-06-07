package net.bodz.violet.edu.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.edu.CourseCategory;

/**
* @label CourseCategory
*/
@ObjectType(CourseCategory.class)
public class CourseCategoryIndex
        extends CoIndex<CourseCategory, CourseCategoryMask> {

    public CourseCategoryIndex() {
    }

}

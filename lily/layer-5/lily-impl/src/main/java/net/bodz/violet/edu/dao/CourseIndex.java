package net.bodz.violet.edu.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.edu.Course;

/**
* @label Course
*/
@ObjectType(Course.class)
public class CourseIndex
        extends CoIndex<Course, CourseCriteriaBuilder> {

    public CourseIndex() {
    }

}

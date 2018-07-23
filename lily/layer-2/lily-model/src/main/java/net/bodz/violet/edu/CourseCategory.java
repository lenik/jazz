package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 课程分类
 */
@Table(name = "coursecat")
@IdType(Integer.class)
public class CourseCategory
        extends CoNode<CourseCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public CourseCategory() {
    }

}

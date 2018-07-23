package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;

/**
 * 课件分类
 */
@Table(name = "coursekitcat")
@IdType(Integer.class)
public class CourseKitCategory
        extends CoCode<CourseKitCategory> {

    private static final long serialVersionUID = 1L;

    public CourseKitCategory() {
    }

}

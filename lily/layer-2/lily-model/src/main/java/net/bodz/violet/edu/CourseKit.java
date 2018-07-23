package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 课件
 */
@Table(name = "coursekit")
@IdType(Integer.class)
public class CourseKit
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    CourseKitCategory category;
    Course course;

    public CourseKit() {
    }

    /**
     * 课件分类
     */
    public CourseKitCategory getCategory() {
        return category;
    }

    public void setCategory(CourseKitCategory category) {
        this.category = category;
    }

    /**
     * 所属课程
     */
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}

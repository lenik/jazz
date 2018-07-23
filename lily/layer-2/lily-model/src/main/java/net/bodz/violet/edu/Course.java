package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 课程
 */
@Table(name = "course")
@IdType(Integer.class)
public class Course
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    CourseCategory category;
    int credit;
    JsonMap plugins;

    public Course() {
    }

    /**
     * 课程分类
     */
    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }

    /**
     * 学分
     */
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * 插件
     */
    public JsonMap getPlugins() {
        return plugins;
    }

    public void setPlugins(JsonMap plugins) {
        this.plugins = plugins;
    }

}

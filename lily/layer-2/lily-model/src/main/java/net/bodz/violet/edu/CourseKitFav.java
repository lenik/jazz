package net.bodz.violet.edu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Integer.class)
public class CourseKitFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    CourseKit courseKit;

    public CourseKitFav() {
    }

    public CourseKit getCourseKit() {
        return courseKit;
    }

    public void setCourseKit(CourseKit courseKit) {
        this.courseKit = courseKit;
    }

}

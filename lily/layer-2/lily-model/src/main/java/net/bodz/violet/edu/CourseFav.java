package net.bodz.violet.edu;

import net.bodz.lily.template.FavRecord;

public class CourseFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    Course course;

    public CourseFav() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}

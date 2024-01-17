package net.bodz.violet.edu;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Long.class)
public abstract class _CourseFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "course_fav";

    public static final String FIELD_COURSE_ID = "course";

    public static final int N_COURSE_ID = 10;

    private static final int _ord_COURSE_ID = 2;

    /**  */
    @NotNull
    Course course;

    @NotNull
    int courseId;

    /**
     *
     * @label course
     * @constraint foreign key (course) references violet.course (id)
     */
    @NotNull
    public Course getCourse() {
        return course;
    }

    /**
     */
    public void setCourse(@NotNull Course value) {
        this.course = value;
    }

    @Ordinal(_ord_COURSE_ID)
    @Precision(value = 10)
    @Column(name = "course", nullable = false, precision = 10)
    public synchronized int getCourseId() {
        if (course != null) {
            return course.getId();
        }
        return courseId;
    }

    public synchronized void setCourseId(int value) {
        this.courseId = value;
    }

    public void initNotNulls() {
    }

}

package net.bodz.violet.schema.edu;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

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
     * @constraint foreign key (course) references violet.course (id)
     */
    @JoinColumn(name = "course")
    @ManyToOne
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
            if (course.getId() == null)
                return 0;
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

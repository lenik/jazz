package net.bodz.violet.schema.edu;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _CourseKitFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "coursekit_fav";

    public static final String FIELD_COURSEKIT_ID = "coursekit";

    public static final int N_COURSEKIT_ID = 10;

    private static final int _ord_COURSEKIT_ID = 2;

    /**  */
    @NotNull
    CourseKit coursekit;

    @NotNull
    int coursekitId;

    /**
     *
     * @label coursekit
     * @constraint foreign key (coursekit) references violet.coursekit (id)
     */
    @NotNull
    public CourseKit getCoursekit() {
        return coursekit;
    }

    /**
     */
    public void setCoursekit(@NotNull CourseKit value) {
        this.coursekit = value;
    }

    @Ordinal(_ord_COURSEKIT_ID)
    @Precision(value = 10)
    @Column(name = "coursekit", nullable = false, precision = 10)
    public synchronized int getCoursekitId() {
        if (coursekit != null) {
            return coursekit.getId();
        }
        return coursekitId;
    }

    public synchronized void setCoursekitId(int value) {
        this.coursekitId = value;
    }

    public void initNotNulls() {
    }

}

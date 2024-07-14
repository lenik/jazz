package net.bodz.violet.schema.edu;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _CourseKit_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "coursekit";

    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_COURSE_ID = "course";
    public static final String FIELD_FAV_COUNT = "nfav";
    public static final String FIELD_VOTE_COUNT = "nvote";
    public static final String FIELD_HATE_COUNT = "nhate";
    public static final String FIELD_DUMMY = "dummy";

    public static final int N_CATEGORY_ID = 10;
    public static final int N_COURSE_ID = 10;
    public static final int N_FAV_COUNT = 10;
    public static final int N_VOTE_COUNT = 10;
    public static final int N_HATE_COUNT = 10;
    public static final int N_DUMMY = 2147483647;

    private static final int _ord_CATEGORY_ID = 15;
    private static final int _ord_COURSE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_FAV_COUNT = _ord_COURSE_ID + 2;
    private static final int _ord_VOTE_COUNT = _ord_FAV_COUNT + 1;
    private static final int _ord_HATE_COUNT = _ord_VOTE_COUNT + 1;
    private static final int _ord_DUMMY = _ord_HATE_COUNT + 1;

    @NotNull
    int favCount;

    @NotNull
    int voteCount;

    @NotNull
    int hateCount;

    JsonVariant dummy;

    /**  */
    @NotNull
    CourseKitCategory category;

    @NotNull
    int categoryId;

    /**  */
    @NotNull
    Course course;

    @NotNull
    int courseId;

    @Ordinal(_ord_FAV_COUNT)
    @Precision(value = 10)
    @Column(name = "nfav", nullable = false, precision = 10)
    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int value) {
        this.favCount = value;
    }

    @Ordinal(_ord_VOTE_COUNT)
    @Precision(value = 10)
    @Column(name = "nvote", nullable = false, precision = 10)
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int value) {
        this.voteCount = value;
    }

    @Ordinal(_ord_HATE_COUNT)
    @Precision(value = 10)
    @Column(name = "nhate", nullable = false, precision = 10)
    public int getHateCount() {
        return hateCount;
    }

    public void setHateCount(int value) {
        this.hateCount = value;
    }

    @Ordinal(_ord_DUMMY)
    @Precision(value = 2147483647)
    @Column(name = "dummy", precision = 2147483647)
    public JsonVariant getDummy() {
        return dummy;
    }

    public void setDummy(JsonVariant value) {
        this.dummy = value;
    }

    /**
     *
     * @constraint foreign key (cat) references violet.coursekitcat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    @NotNull
    public CourseKitCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull CourseKitCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            if (category.getId() == null)
                return 0;
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

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

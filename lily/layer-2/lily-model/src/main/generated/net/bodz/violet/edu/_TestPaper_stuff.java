package net.bodz.violet.edu;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _TestPaper_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_COURSE_ID = 14;
    private static final int _ord_TIMEOUT = _ord_COURSE_ID + 1;
    private static final int _ord_TOTALSCORE = _ord_TIMEOUT + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    int timeout;

    @NotNull
    int totalscore;

    /**  */
    @NotNull
    Course course;

    @NotNull
    int courseId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_TIMEOUT)
    @Precision(value = 10)
    @Column(name = "timeout", nullable = false, precision = 10)
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int value) {
        this.timeout = value;
    }

    @Ordinal(_ord_TOTALSCORE)
    @Precision(value = 10)
    @Column(name = "totalscore", nullable = false, precision = 10)
    public int getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(int value) {
        this.totalscore = value;
    }

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

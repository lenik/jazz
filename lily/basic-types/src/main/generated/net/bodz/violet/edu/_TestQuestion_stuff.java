package net.bodz.violet.edu;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
public abstract class _TestQuestion_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testq";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_COURSE_ID = "course";
    public static final String FIELD_FAV_COUNT = "nfav";
    public static final String FIELD_VOTE_COUNT = "nvote";
    public static final String FIELD_HATE_COUNT = "nhate";
    public static final String FIELD_POS = "pos";
    public static final String FIELD_ANSWER = "answer";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_COURSE_ID = 10;
    public static final int N_FAV_COUNT = 10;
    public static final int N_VOTE_COUNT = 10;
    public static final int N_HATE_COUNT = 10;
    public static final int N_POS = 10;
    public static final int N_ANSWER = 100;

    private static final int _ord_FORM_ARGUMENTS = 16;
    private static final int _ord_COURSE_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_FAV_COUNT = _ord_COURSE_ID + 2;
    private static final int _ord_VOTE_COUNT = _ord_FAV_COUNT + 1;
    private static final int _ord_HATE_COUNT = _ord_VOTE_COUNT + 1;
    private static final int _ord_POS = _ord_HATE_COUNT + 1;
    private static final int _ord_ANSWER = _ord_POS + 1;

    String formArguments;

    @NotNull
    int favCount;

    @NotNull
    int voteCount;

    @NotNull
    int hateCount;

    @NotNull
    int pos;

    @NotNull
    String answer;

    /**  */
    @NotNull
    Course course;

    @NotNull
    int courseId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

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

    @Ordinal(_ord_POS)
    @Precision(value = 10)
    @Column(name = "pos", nullable = false, precision = 10)
    public int getPos() {
        return pos;
    }

    public void setPos(int value) {
        this.pos = value;
    }

    @Ordinal(_ord_ANSWER)
    @NotNull
    @Precision(value = N_ANSWER)
    @TextInput(maxLength = N_ANSWER)
    @Column(name = "answer", nullable = false, length = N_ANSWER)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NotNull String value) {
        this.answer = value;
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
        this.answer = "";
    }

}

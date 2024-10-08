package net.bodz.violet.schema.edu;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _TestApplyItem_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testapplyl";

    public static final String FIELD_APPLY_ID = "apply";
    public static final String FIELD_QUESTION_ID = "q";
    public static final String FIELD_ANSWER = "ans";
    public static final String FIELD_ANSTEXT = "anstext";
    public static final String FIELD_SCORE = "score";
    public static final String FIELD_WAITTIME = "waittime";

    public static final int N_APPLY_ID = 19;
    public static final int N_QUESTION_ID = 19;
    public static final int N_ANSWER = 10;
    public static final int N_ANSTEXT = 200;
    public static final int N_SCORE = 17;
    public static final int N_WAITTIME = 17;

    private static final int _ord_APPLY_ID = 8;
    private static final int _ord_QUESTION_ID = _ord_APPLY_ID + 1;
    private static final int _ord_ANSWER = _ord_QUESTION_ID + 1;
    private static final int _ord_ANSTEXT = _ord_ANSWER + 1;
    private static final int _ord_SCORE = _ord_ANSTEXT + 1;
    private static final int _ord_WAITTIME = _ord_SCORE + 1;

    Integer answer;

    String anstext;

    Double score;

    Double waittime;

    /**  */
    @NotNull
    TestQuestion question;

    @NotNull
    long questionId;

    /**  */
    @NotNull
    TestApply apply;

    @NotNull
    long applyId;

    @Ordinal(_ord_ANSWER)
    @Precision(value = N_ANSWER)
    @Column(name = "ans", precision = 10)
    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer value) {
        this.answer = value;
    }

    @Ordinal(_ord_ANSTEXT)
    @Precision(value = N_ANSTEXT)
    @TextInput(maxLength = N_ANSTEXT)
    @Column(name = "anstext", length = N_ANSTEXT)
    public String getAnstext() {
        return anstext;
    }

    public void setAnstext(String value) {
        this.anstext = value;
    }

    @Ordinal(_ord_SCORE)
    @Precision(value = N_SCORE, scale = 17)
    @Column(name = "score", precision = 17, scale = 17)
    public Double getScore() {
        return score;
    }

    public void setScore(Double value) {
        this.score = value;
    }

    @Ordinal(_ord_WAITTIME)
    @Precision(value = N_WAITTIME, scale = 17)
    @Column(name = "waittime", precision = 17, scale = 17)
    public Double getWaittime() {
        return waittime;
    }

    public void setWaittime(Double value) {
        this.waittime = value;
    }

    /**
     *
     * @constraint foreign key (q) references violet.testq (id)
     */
    @JoinColumn(name = "q")
    @ManyToOne
    @NotNull
    public TestQuestion getQuestion() {
        return question;
    }

    /**
     */
    public void setQuestion(@NotNull TestQuestion value) {
        this.question = value;
    }

    @Ordinal(_ord_QUESTION_ID)
    @Precision(value = 19)
    @Column(name = "q", nullable = false, precision = 19)
    public synchronized long getQuestionId() {
        if (question != null) {
            if (question.getId() == null)
                return 0L;
            return question.getId();
        }
        return questionId;
    }

    public synchronized void setQuestionId(long value) {
        this.questionId = value;
    }

    /**
     *
     * @constraint foreign key (apply) references violet.testapply (id)
     */
    @JoinColumn(name = "apply")
    @ManyToOne
    @NotNull
    public TestApply getApply() {
        return apply;
    }

    /**
     */
    public void setApply(@NotNull TestApply value) {
        this.apply = value;
    }

    @Ordinal(_ord_APPLY_ID)
    @Precision(value = 19)
    @Column(name = "apply", nullable = false, precision = 19)
    public synchronized long getApplyId() {
        if (apply != null) {
            if (apply.getId() == null)
                return 0L;
            return apply.getId();
        }
        return applyId;
    }

    public synchronized void setApplyId(long value) {
        this.applyId = value;
    }

    public void initNotNulls() {
    }

}

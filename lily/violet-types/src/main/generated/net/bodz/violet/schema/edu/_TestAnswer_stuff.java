package net.bodz.violet.schema.edu;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _TestAnswer_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testans";

    public static final String FIELD_QUESTION_ID = "q";
    public static final String FIELD_VALID = "valid";

    public static final int N_QUESTION_ID = 19;
    public static final int N_VALID = 1;

    private static final int _ord_QUESTION_ID = 13;
    private static final int _ord_VALID = _ord_QUESTION_ID + 1;

    @NotNull
    boolean valid;

    /**  */
    @NotNull
    TestQuestion question;

    @NotNull
    long questionId;

    @Ordinal(_ord_VALID)
    @Precision(value = 1)
    @Column(name = "valid", nullable = false, precision = 1)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        this.valid = value;
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

    public void initNotNulls() {
    }

}

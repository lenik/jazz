package net.bodz.violet.edu;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _TestAnswer_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_QUESTION_ID = _ord_ID + 10;
    private static final int _ord_VALID = _ord_QUESTION_ID + 1;

    @Id
    @NotNull
    long id;

    @NotNull
    boolean valid;

    /**  */
    @NotNull
    TestQuestion question;

    @NotNull
    long questionId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

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
     * @label q
     * @constraint foreign key (q) references violet.testq (id)
     */
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

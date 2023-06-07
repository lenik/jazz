package net.bodz.violet.edu;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _TestPaperItem_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_SCORE = 6;

    private static final int _ord_ID = 1;
    private static final int _ord_PAPER_ID = _ord_ID + 8;
    private static final int _ord_QUESTION_ID = _ord_PAPER_ID + 1;
    private static final int _ord_SCORE = _ord_QUESTION_ID + 1;

    @Id
    @NotNull
    long id;

    BigDecimal score;

    /**  */
    @NotNull
    TestQuestion question;

    @NotNull
    long questionId;

    /**  */
    @NotNull
    TestPaper paper;

    @NotNull
    int paperId;

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

    @Ordinal(_ord_SCORE)
    @Precision(value = N_SCORE, scale = 2)
    @Column(name = "score", precision = 6, scale = 2)
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal value) {
        this.score = value;
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

    /**
     *
     * @label paper
     * @constraint foreign key (paper) references violet.testpaper (id)
     */
    @NotNull
    public TestPaper getPaper() {
        return paper;
    }

    /**
     */
    public void setPaper(@NotNull TestPaper value) {
        this.paper = value;
    }

    @Ordinal(_ord_PAPER_ID)
    @Precision(value = 10)
    @Column(name = "paper", nullable = false, precision = 10)
    public synchronized int getPaperId() {
        if (paper != null) {
            return paper.getId();
        }
        return paperId;
    }

    public synchronized void setPaperId(int value) {
        this.paperId = value;
    }

    public void initNotNulls() {
    }

}

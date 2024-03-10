package net.bodz.violet.schema.edu;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _TestPaperItem_stuff
        extends CoImaged<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testpaperl";

    public static final String FIELD_PAPER_ID = "paper";
    public static final String FIELD_QUESTION_ID = "q";
    public static final String FIELD_SCORE = "score";

    public static final int N_PAPER_ID = 10;
    public static final int N_QUESTION_ID = 19;
    public static final int N_SCORE = 6;

    private static final int _ord_PAPER_ID = 9;
    private static final int _ord_QUESTION_ID = _ord_PAPER_ID + 1;
    private static final int _ord_SCORE = _ord_QUESTION_ID + 1;

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
     * @constraint foreign key (paper) references violet.testpaper (id)
     */
    @JoinColumn(name = "paper")
    @ManyToOne
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
            if (paper.getId() == null)
                return 0;
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

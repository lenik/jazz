package net.bodz.violet.edu.dao;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.edu.TestPaperItem
 */
public class TestPaperItemCriteriaBuilder
        extends CoObjectCriteriaBuilder {

    Integer paperId;
    Long questionId;
    DoubleRange scoreRange;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public DoubleRange getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(DoubleRange scoreRange) {
        this.scoreRange = scoreRange;
    }

}

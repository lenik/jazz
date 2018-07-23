package net.bodz.violet.edu.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.TestAnswer
 */
public class TestAnswerMask
        extends CoObjectMask {

    Long questionId;
    Boolean valid;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

}

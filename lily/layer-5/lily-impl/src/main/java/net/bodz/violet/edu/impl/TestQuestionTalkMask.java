package net.bodz.violet.edu.impl;

import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.edu.TestQuestionTalk
 */
public class TestQuestionTalkMask
        extends CoMessageMask {

    Long question;
    Long parentId;

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}

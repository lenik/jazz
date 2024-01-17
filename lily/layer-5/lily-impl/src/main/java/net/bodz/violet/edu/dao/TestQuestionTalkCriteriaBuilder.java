package net.bodz.violet.edu.dao;

import net.bodz.lily.t.base.CoMessageCriteriaBuilder;

/**
 * @see net.bodz.violet.edu.TestQuestionTalk
 */
public class TestQuestionTalkCriteriaBuilder
        extends CoMessageCriteriaBuilder {

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

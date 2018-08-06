package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * 题目讨论
 */
@Table(name = "testq_msg")
@IdType(Long.class)
public class TestQuestionMsg
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    TestQuestion question;
    TestQuestionMsg parent;

    public TestQuestionMsg() {
    }

    /**
     * 所属题目
     */
    public TestQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TestQuestion question) {
        this.question = question;
    }

    /**
     * 父节点
     */
    public TestQuestionMsg getParent() {
        return parent;
    }

    public void setParent(TestQuestionMsg parent) {
        this.parent = parent;
    }

}

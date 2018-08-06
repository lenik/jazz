package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.template.CoTalk;

/**
 * 题目讨论
 */
@Table(name = "testq_msg")
public class TestQuestionTalk
        extends CoTalk<TestQuestionTalk> {

    private static final long serialVersionUID = 1L;

    TestQuestion question;

    public TestQuestionTalk() {
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

}

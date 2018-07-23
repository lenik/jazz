package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 交卷答案明细
 */
@Table(name = "testapplyl")
@IdType(Long.class)
public class TestApplyItem
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    TestApply apply;
    TestQuestion question;
    int answerInt;
    String answerText;
    double score;
    float waitTime;

    public TestApplyItem() {
    }

    /**
     * 所属交卷
     */
    public TestApply getApply() {
        return apply;
    }

    public void setApply(TestApply apply) {
        this.apply = apply;
    }

    /**
     * 题目
     */
    public TestQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TestQuestion question) {
        this.question = question;
    }

    /**
     *
     */
    public int getAnswerInt() {
        return answerInt;
    }

    public void setAnswerInt(int answerInt) {
        this.answerInt = answerInt;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public float getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(float waitTime) {
        this.waitTime = waitTime;
    }

}

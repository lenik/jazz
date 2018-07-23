package net.bodz.violet.edu.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.TestQuestion
 */
public class TestQuestionMask
        extends CoObjectMask {

    Integer courseId;
    String answerPattern;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getAnswerPattern() {
        return answerPattern;
    }

    public void setAnswerPattern(String answerPattern) {
        this.answerPattern = answerPattern;
    }

}

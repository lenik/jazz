package net.bodz.violet.edu.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

/**
 * @see net.bodz.violet.edu.TestQuestion
 */
public class TestQuestionCriteriaBuilder
        extends CoObjectCriteriaBuilder {

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

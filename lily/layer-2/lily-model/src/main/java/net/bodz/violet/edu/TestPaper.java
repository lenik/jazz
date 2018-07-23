package net.bodz.violet.edu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 考卷
 */
@Table(name = "testpaper")
@IdType(Integer.class)
public class TestPaper
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    Course course;
    int timeout;
    int totalScore;

    public TestPaper() {
    }

    /**
     * 所属课程
     */
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * 考试时间
     */
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 考卷总分（计算）
     */
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

}

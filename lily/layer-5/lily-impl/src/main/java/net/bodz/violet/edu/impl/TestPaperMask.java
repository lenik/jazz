package net.bodz.violet.edu.impl;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.TestPaper
 */
public class TestPaperMask
        extends CoObjectMask {

    Integer course;
    IntegerRange timeoutRange;
    IntegerRange totalScoreRange;

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public IntegerRange getTimeoutRange() {
        return timeoutRange;
    }

    public void setTimeoutRange(IntegerRange timeoutRange) {
        this.timeoutRange = timeoutRange;
    }

    public IntegerRange getTotalScoreRange() {
        return totalScoreRange;
    }

    public void setTotalScoreRange(IntegerRange totalScoreRange) {
        this.totalScoreRange = totalScoreRange;
    }

}

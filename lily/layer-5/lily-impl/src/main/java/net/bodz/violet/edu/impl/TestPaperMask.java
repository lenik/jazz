package net.bodz.violet.edu.impl;

import net.bodz.bas.t.range.IntRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.TestPaper
 */
public class TestPaperMask
        extends CoObjectMask {

    Integer course;
    IntRange timeoutRange;
    IntRange totalScoreRange;

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public IntRange getTimeoutRange() {
        return timeoutRange;
    }

    public void setTimeoutRange(IntRange timeoutRange) {
        this.timeoutRange = timeoutRange;
    }

    public IntRange getTotalScoreRange() {
        return totalScoreRange;
    }

    public void setTotalScoreRange(IntRange totalScoreRange) {
        this.totalScoreRange = totalScoreRange;
    }

}

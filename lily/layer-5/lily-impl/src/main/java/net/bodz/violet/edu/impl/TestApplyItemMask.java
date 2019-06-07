package net.bodz.violet.edu.impl;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.range.FloatRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.edu.TestApplyItem
 */
public class TestApplyItemMask
        extends CoObjectMask {

    Integer apply;
    Long question;
    DoubleRange scoreRange;
    FloatRange waitTimeRange;

    public Integer getApply() {
        return apply;
    }

    public void setApply(Integer apply) {
        this.apply = apply;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public DoubleRange getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(DoubleRange scoreRange) {
        this.scoreRange = scoreRange;
    }

    public FloatRange getWaitTimeRange() {
        return waitTimeRange;
    }

    public void setWaitTimeRange(FloatRange waitTimeRange) {
        this.waitTimeRange = waitTimeRange;
    }

}

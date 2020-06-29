package net.bodz.violet.plan.impl;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.t.base.CoMessageMask;

/**
 * @see net.bodz.violet.plan.Diary
 */
public class DiaryMask
        extends CoMessageMask {

    IntegerRange valueRange;

    public IntegerRange getValueRange() {
        return valueRange;
    }

    public void setValueRange(IntegerRange valueRange) {
        this.valueRange = valueRange;
    }

}

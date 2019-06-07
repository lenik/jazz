package net.bodz.violet.plan.impl;

import net.bodz.bas.t.range.IntRange;
import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.plan.Diary
 */
public class DiaryMask
        extends CoMessageMask {

    IntRange valueRange;

    public IntRange getValueRange() {
        return valueRange;
    }

    public void setValueRange(IntRange valueRange) {
        this.valueRange = valueRange;
    }

}

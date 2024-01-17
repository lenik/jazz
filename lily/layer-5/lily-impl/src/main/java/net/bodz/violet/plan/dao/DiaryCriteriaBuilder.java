package net.bodz.violet.plan.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.t.base.CoMessageCriteriaBuilder;

/**
 * @see net.bodz.violet.plan.Diary
 */
public class DiaryCriteriaBuilder
        extends CoMessageCriteriaBuilder {

    IntegerRange valueRange;

    public IntegerRange getValueRange() {
        return valueRange;
    }

    public void setValueRange(IntegerRange valueRange) {
        this.valueRange = valueRange;
    }

}

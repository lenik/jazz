package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public class NumberFieldCriterionBuilder<fin_target, T extends Number>
        extends FieldCriterionBuilder<fin_target, NumberFieldCriterionBuilder<fin_target, T>, T> {

    public NumberFieldCriterionBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName, type, finishTarget, receiver);
    }

}

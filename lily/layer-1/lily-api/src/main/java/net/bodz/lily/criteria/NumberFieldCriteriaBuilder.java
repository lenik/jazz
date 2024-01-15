package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;

public class NumberFieldCriteriaBuilder<fin_target, T extends Number>
        extends FieldCriteriaBuilder<fin_target, NumberFieldCriteriaBuilder<fin_target, T>, T> {

    public NumberFieldCriteriaBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, type, finishTarget, receiver);
    }

}

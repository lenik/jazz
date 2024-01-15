package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;

public class NumberFieldCriteriaBuilder<fin_target>
        extends FieldCriteriaBuilder<fin_target, NumberFieldCriteriaBuilder<fin_target>, Number> {

    public NumberFieldCriteriaBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, finishTarget, receiver);
    }

}

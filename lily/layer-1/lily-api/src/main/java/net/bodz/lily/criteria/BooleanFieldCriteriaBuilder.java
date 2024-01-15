package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;

public class BooleanFieldCriteriaBuilder<fin_target>
        extends FieldCriteriaBuilder<fin_target, BooleanFieldCriteriaBuilder<fin_target>, Boolean> {

    public BooleanFieldCriteriaBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, finishTarget, receiver);
    }

}

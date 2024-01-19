package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public class BooleanFieldCriterionBuilder<fin_target>
        extends FieldCriterionBuilder<fin_target, BooleanFieldCriterionBuilder<fin_target>, Boolean> {

    public BooleanFieldCriterionBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName, Boolean.class, finishTarget, receiver);
    }

}

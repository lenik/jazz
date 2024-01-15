package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;

public class NumberExprCriteriaBuilder<fin_target>
        extends ExprCriteriaBuilder<fin_target, NumberExprCriteriaBuilder<fin_target>> {

    public NumberExprCriteriaBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, finishTarget, receiver);
    }

}

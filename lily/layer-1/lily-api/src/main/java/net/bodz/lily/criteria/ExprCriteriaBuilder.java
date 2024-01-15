package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;

public class ExprCriteriaBuilder<fin_target, self_t> {

    String expr;

    fin_target finishTarget;

    IReceiver<? super FieldCriterion> receiver;

    public ExprCriteriaBuilder(String expr, fin_target finishTarget, IReceiver<? super FieldCriterion> receiver) {
        this.expr = expr;
        this.finishTarget = finishTarget;
    }

}

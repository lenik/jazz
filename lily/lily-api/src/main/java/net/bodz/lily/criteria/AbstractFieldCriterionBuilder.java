package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public abstract class AbstractFieldCriterionBuilder<fin_target, This, T>
        extends AbstractDiscreteFieldCriterionBuilder<fin_target, This, T>
        implements
            IFieldCriterionBuilder<T>,
            IFieldCriterionSender<fin_target, This, T> {

    public AbstractFieldCriterionBuilder(fin_target finishTarget, IReceiver<? super ICriterion> receiver) {
        super(finishTarget, receiver);
    }

}

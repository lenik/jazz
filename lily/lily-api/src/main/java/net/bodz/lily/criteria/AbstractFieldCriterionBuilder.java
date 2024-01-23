package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public abstract class AbstractFieldCriterionBuilder<fin_target, This, T>
        implements
            IFieldCriterionBuilder<T>,
            IFieldCriterionSender<fin_target, This, T> {

    fin_target finishTarget;
    IReceiver<? super ICriterion> receiver;

    public AbstractFieldCriterionBuilder(fin_target finishTarget, IReceiver<? super ICriterion> receiver) {
        this.finishTarget = finishTarget;
        this.receiver = receiver;
    }

    @Override
    public fin_target send(ICriterion criterion) {
        if (criterion != null)
            receiver.receive(criterion);
        return finishTarget;
    }

}

package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public abstract class AbstractDiscreteFieldCriterionBuilder<fin_target, This, T>
        implements
            IDiscreteFieldCriterionBuilder<T>,
            IDiscreteFieldCriterionSender<fin_target, This, T> {

    fin_target finishTarget;
    IReceiver<? super ICriterion> receiver;

    public AbstractDiscreteFieldCriterionBuilder(fin_target finishTarget, IReceiver<? super ICriterion> receiver) {
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

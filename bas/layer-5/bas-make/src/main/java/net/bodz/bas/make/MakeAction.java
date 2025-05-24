package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class MakeAction<T extends IKeyData<?, ?>> {

    final IMakeRule<T> rule;
    final T target;
    final IKeyData<?, ?>[] inputs;

    public MakeAction(IMakeRule<T> rule, T target, @NotNull IKeyData<?, ?>... inputs) {
        this.rule = rule;
        this.target = target;
        this.inputs = inputs;
    }

    public T getTarget() {
        return target;
    }

    public IMakeRule<T> getRule() {
        return rule;
    }

    public IKeyData<?, ?>[] getInputs() {
        return inputs;
    }

    public double getCost() {
        return rule.getEstimatedCost(target, inputs);
    }

    public void run()
            throws MakeException {
        rule.make(target, inputs);
    }

}

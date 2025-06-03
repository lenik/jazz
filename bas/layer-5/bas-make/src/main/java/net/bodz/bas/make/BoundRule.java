package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class BoundRule<T extends IKeyData<TK, TT>, TK, TT> {

    final IMakeRule<T, TK, TT> rule;
    final T target;
    final IKeyData<?, ?>[] inputs;

    public BoundRule(IMakeRule<T, TK, TT> rule, T target, @NotNull IKeyData<?, ?>... inputs) {
        this.rule = rule;
        this.target = target;
        this.inputs = inputs;
    }

    public static <T extends IKeyData<TK, TT>, TK, TT> BoundRule<T, TK, TT> of(@NotNull IMakeRule<T, TK, TT> rule, @NotNull T target, @NotNull IKeyData<?, ?>... inputs) {
        return new BoundRule<>(rule, target, inputs);
    }

    public T getTarget() {
        return target;
    }

    public IMakeRule<T, TK, TT> getRule() {
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

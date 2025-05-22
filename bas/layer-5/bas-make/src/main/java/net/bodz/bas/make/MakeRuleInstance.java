package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class MakeRuleInstance<T extends IKeyData<?, ?>> {

    final IMakeRule<T> rule;
    final IKeyData<?, ?>[] inputs;

    public MakeRuleInstance(IMakeRule<T> rule, @NotNull IKeyData<?, ?>... inputs) {
        this.rule = rule;
        this.inputs = inputs;
    }

    public IMakeRule<T> getRule() {
        return rule;
    }

    public IKeyData<?, ?>[] getInputs() {
        return inputs;
    }

}

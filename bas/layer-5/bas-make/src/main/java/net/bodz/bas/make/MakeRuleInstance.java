package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public class MakeRuleInstance<T extends IDataEntry<?, ?>> {

    final IMakeRule<T> rule;
    final IDataEntry<?, ?>[] inputs;

    public MakeRuleInstance(IMakeRule<T> rule, @NotNull IDataEntry<?, ?>... inputs) {
        this.rule = rule;
        this.inputs = inputs;
    }

    public IMakeRule<T> getRule() {
        return rule;
    }

    public IDataEntry<?, ?>[] getInputs() {
        return inputs;
    }

}

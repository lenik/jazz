package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IPatternMakeRule<Tp extends IKeyPattern<?, K>, K, T extends IKeyData<K, ?>>
        extends IPriority {

    Tp getPattern();

    IParameterizedKeys<?, ?>[] getInputs();

    MakeRuleInstance<T> compile(@NotNull T target, @NotNull IMakeSession session)
            throws MakeException;

}

package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction<T extends IKeyPattern<?, K>, K, D extends IDataEntry<K, ?>> {

    MakeRuleInstance<D> compile(@NotNull D target, @NotNull T pattern, @NotNull IParameterizedKeys<?, ?>... inputss)
            throws MakeException;

}

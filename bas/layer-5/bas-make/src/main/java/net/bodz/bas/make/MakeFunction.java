package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface MakeFunction<T extends IDataEntry<?, ?>> {

    void make(@NotNull T target, @NotNull IDataEntry<?, ?>... inputs)
            throws MakeException;

}

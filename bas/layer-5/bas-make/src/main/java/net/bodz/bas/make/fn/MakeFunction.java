package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.MakeException;
import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface MakeFunction<T extends IKeyData<?, ?>> {

    void make(@NotNull T target, @NotNull IKeyData<?, ?>... inputs)
            throws MakeException;

}

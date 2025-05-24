package net.bodz.bas.make.fn;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.meta.decl.NotNull;

public interface CompileFunction<T extends IKeyData<?, ?>> {

    MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs);

}

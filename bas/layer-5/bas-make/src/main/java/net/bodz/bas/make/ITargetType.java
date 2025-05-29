package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface ITargetType<T extends IKeyData<TK, TT>, TK, TT> {

    @NotNull
    Class<? extends T> getTargetType();

}

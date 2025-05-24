package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IKeyType<K> {

    @NotNull
    Class<? extends K> getKeyType();

}

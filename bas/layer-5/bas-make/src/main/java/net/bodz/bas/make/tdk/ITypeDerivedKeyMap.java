package net.bodz.bas.make.tdk;

import java.util.Map;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.meta.decl.NotNull;

public interface ITypeDerivedKeyMap<E, EK, K>
        extends IKeyData<MapKey<E, EK, K>, Map<EK, E>> {

    @NotNull
    default Class<? extends EK> getElementKeyType() {
        return getKey().getElementKeyType();
    }

    @NotNull
    default Class<? extends E> getElementValueType() {
        return getKey().getElementValueType();
    }

    default Class<? extends E> getDerivedFromType() {
        return getElementValueType();
    }

    @NotNull
    Class<? extends K> getDerivedKeyType();

    @NotNull
    default K getDerivedKey() {
        return getKey().getWrappedKey();
    }

}

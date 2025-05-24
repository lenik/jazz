package net.bodz.bas.make.tdk;

import java.util.Map;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.meta.decl.NotNull;

public interface ITypeDerivedKeyMap<K, EK, EV>
        extends IKeyData<MapKey<EK, EV, K>, Map<EK, EV>> {

    @NotNull
    default Class<? extends EK> getElementKeyType() {
        return getKey().getElementKeyType();
    }

    @NotNull
    default Class<? extends EV> getElementValueType() {
        return getKey().getElementValueType();
    }

    default Class<? extends EV> getDerivedFromType() {
        return getElementValueType();
    }

    @NotNull
    Class<? extends K> getDerivedKeyType();

    @NotNull
    default K getDerivedKey() {
        return getKey().getWrappedKey();
    }

}

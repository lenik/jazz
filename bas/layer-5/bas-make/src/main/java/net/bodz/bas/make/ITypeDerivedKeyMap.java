package net.bodz.bas.make;

import java.util.Map;

import net.bodz.bas.make.util.MapKey;
import net.bodz.bas.meta.decl.NotNull;

public interface ITypeDerivedKeyMap<K, EK, EV>
        extends IKeyData<MapKey<K, EK, EV>, Map<EK, EV>> {

    @NotNull
    default Class<EK> getElementKeyType() {
        return getKey().getElementKeyType();
    }

    @NotNull
    default Class<EV> getElementValueType() {
        return getKey().getElementValueType();
    }

    default Class<EV> getDerivedFromType() {
        return getElementValueType();
    }

    @NotNull
    Class<K> getDerivedKeyType();

    @NotNull
    default K getDerivedKey() {
        return getKey().getWrappedKey();
    }

}

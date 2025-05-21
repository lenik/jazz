package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IKey<K>
        extends IParameterizedKeys<Object, K> {

    @NotNull
    Class<K> getKeyType();

    @NotNull
    K getKey();

    @Override
    default Class<Object> getParameterType() {
        return Object.class;
    }

    @Override
    default K getKey(Object parameter) {
        return getKey();
    }

}

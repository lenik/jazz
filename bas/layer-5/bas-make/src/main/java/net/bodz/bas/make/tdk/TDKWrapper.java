package net.bodz.bas.make.tdk;

import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.IWrapper;

public class TDKWrapper<E, K>
        implements ITypeDerivedKey<E, K>,
                   IWrapper<ITypeDerivedKey<E, K>> {

    ITypeDerivedKey<E, K> wrapped;

    public TDKWrapper(@NotNull ITypeDerivedKey<E, K> wrapped) {
        this.wrapped = wrapped;
    }

    @NotNull
    @Override
    public ITypeDerivedKey<E, K> getWrapped() {
        return wrapped;
    }

    @NotNull
    @Override
    public Class<? extends E> getDerivedFromType() {
        return wrapped.getDerivedFromType();
    }

    @NotNull
    @Override
    public Class<? extends K> getDerivedKeyType() {
        return wrapped.getDerivedKeyType();
    }

    @NotNull
    @Override
    public K getDerivedKey() {
        return wrapped.getDerivedKey();
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrapped.getDerivedFromType(), wrapped.getDerivedKey());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof ITypeDerivedKey<?, ?>))
            return false;
        ITypeDerivedKey<?, ?> o = (ITypeDerivedKey<?, ?>) obj;
        if (!o.getDerivedFromType().equals(wrapped.getDerivedFromType()))
            return false;
        if (!o.getDerivedKey().equals(wrapped.getDerivedKey()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getKeyString();
    }

}

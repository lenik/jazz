package net.bodz.bas.make.tdk;

import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Select the derivedKey from TypeDerivedKeyList/Set/...
 */
public class DataTypedTDKPattern<K extends ITypeDerivedKey<?, ?>, T>
        implements IDataTypedKeyPattern<K, K, T> {

    Class<K> tdkType;
    Class<? extends T> dataType;

    public DataTypedTDKPattern(@NotNull Class<K> tdkType, @NotNull Class<? extends T> dataType) {
        this.tdkType = tdkType;
        this.dataType = dataType;
    }

    @NotNull
    @Override
    public Class<K> getParameterType() {
        return tdkType;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends K> getKeyType() {
        return (Class<? extends K>) (Class<?>) ITypeDerivedKey.class;
    }

    @NotNull
    @Override
    public Class<? extends T> getDataType() {
        return dataType;
    }

    @Override
    public K match(Object key) {
        if (!(key instanceof ITypeDerivedKey<?, ?>))
            return null;
        @SuppressWarnings("unchecked")
        K tdk = (K) key;
        return tdk;
    }

}

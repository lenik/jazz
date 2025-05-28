package net.bodz.bas.make.tdk;

import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Select the derivedKey from TypeDerivedKeyList/Set/...
 */
public class DataTypedDerivedKeyPattern<DerivedKey, T>
        implements IDataTypedKeyPattern<DerivedKey, ITypeDerivedKey<?, DerivedKey>, T> {

    Class<DerivedKey> derivedKeyType;
    Class<? extends T> dataType;

    public DataTypedDerivedKeyPattern(@NotNull Class<DerivedKey> derivedKeyType, @NotNull Class<? extends T> dataType) {
        this.derivedKeyType = derivedKeyType;
        this.dataType = dataType;
    }

    @NotNull
    @Override
    public Class<DerivedKey> getParameterType() {
        return derivedKeyType;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends ITypeDerivedKey<?, DerivedKey>> getKeyType() {
        return (Class<ITypeDerivedKey<?, DerivedKey>>) (Class<?>) ITypeDerivedKey.class;
    }

    @NotNull
    @Override
    public Class<? extends T> getDataType() {
        return dataType;
    }

    @Override
    public DerivedKey match(Object key) {
        if (!(key instanceof ITypeDerivedKey<?, ?>))
            return null;
        @SuppressWarnings("unchecked")
        ITypeDerivedKey<?, DerivedKey> tdk = (ITypeDerivedKey<?, DerivedKey>) key;
        return tdk.getDerivedKey();
    }

}

package net.bodz.bas.make.tdk;

import net.bodz.bas.make.pattern.key.IKeyPattern;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Select the derivedKey from TypeDerivedKeyList/Set/...
 */
public class DerivedKeyPattern<DerivedKey>
        implements IKeyPattern<DerivedKey, ITypeDerivedKey<?, DerivedKey>> {

    Class<DerivedKey> derivedKeyType;

    public DerivedKeyPattern(@NotNull Class<DerivedKey> derivedKeyType) {
        this.derivedKeyType = derivedKeyType;
    }

    @NotNull
    @Override
    public Class<DerivedKey> getParameterType() {
        return derivedKeyType;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<ITypeDerivedKey<?, DerivedKey>> getKeyType() {
        return (Class<ITypeDerivedKey<?, DerivedKey>>) (Class<?>) ITypeDerivedKey.class;
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

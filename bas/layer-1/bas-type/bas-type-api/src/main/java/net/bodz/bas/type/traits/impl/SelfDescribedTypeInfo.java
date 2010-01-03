package net.bodz.bas.type.traits.impl;

import net.bodz.bas.type.traits.ITypeInfo;

public abstract class SelfDescribedTypeInfo
        implements ITypeInfo {

    @Override
    public Object query(String specId) {
        try {
            Class<?> specType = Class.forName(specId);
            return query(specType);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public <_T> _T query(Class<_T> specType) {
        if (specType.isInstance(this))
            return specType.cast(this);
        return null;
    }

}

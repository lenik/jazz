package net.bodz.bas.typeinfo.impl;

import net.bodz.bas.type.ITypeInfo;

public class SelfDescribedTypeInfo
        implements ITypeInfo {

    @Override
    public Object query(String infoId) {
        return null;
    }

    @Override
    public <T> T query(Class<T> infoClass) {
        try {
            T infoImpl = infoClass.cast(this);
            return infoImpl;
        } catch (ClassCastException e) {
            return null;
        }
    }

}

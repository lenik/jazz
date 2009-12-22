package net.bodz.bas.typeinfo.util;

import net.bodz.bas.typeinfo.TypeInfo;

public class SelfDescribedTypeInfo
        implements TypeInfo {

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

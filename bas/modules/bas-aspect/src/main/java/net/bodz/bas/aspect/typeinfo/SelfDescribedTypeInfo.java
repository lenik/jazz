package net.bodz.bas.aspect.typeinfo;

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

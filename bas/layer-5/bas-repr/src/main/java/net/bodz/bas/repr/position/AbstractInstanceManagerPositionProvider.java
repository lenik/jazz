package net.bodz.bas.repr.position;

public class AbstractInstanceManagerPositionProvider
        extends SingleObjectPositionProvider {

    private Class<?> instanceType;

    public AbstractInstanceManagerPositionProvider(Class<?> instanceType) {
        if (instanceType == null)
            throw new NullPointerException("instanceType");
        this.instanceType = instanceType;
    }

    @Override
    protected IObjectOccurrence find(Object object) {
        return null;
    }

}

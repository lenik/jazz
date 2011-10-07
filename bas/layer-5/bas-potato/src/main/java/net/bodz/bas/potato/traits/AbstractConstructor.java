package net.bodz.bas.potato.traits;

public abstract class AbstractConstructor
        extends AbstractElement
        implements IConstructor {

    public AbstractConstructor(Class<?> declaringType) {
        super(declaringType, null);
    }

}

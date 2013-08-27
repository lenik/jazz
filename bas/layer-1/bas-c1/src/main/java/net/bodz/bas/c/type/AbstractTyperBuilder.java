package net.bodz.bas.c.type;

public abstract class AbstractTyperBuilder
        implements ITyperBuilder {

    final Class<?> typerClass;

    public AbstractTyperBuilder(Class<?> typerClass) {
        if (typerClass == null)
            throw new NullPointerException("typerClass");
        this.typerClass = typerClass;
    }

    @Override
    public Class<?> getFeatureClass() {
        return typerClass;
    }

}

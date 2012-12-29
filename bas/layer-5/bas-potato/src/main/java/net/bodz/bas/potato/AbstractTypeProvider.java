package net.bodz.bas.potato;

public abstract class AbstractTypeProvider
        implements ITypeProvider {

    @Override
    public int getPriority() {
        return 0;
    }

}

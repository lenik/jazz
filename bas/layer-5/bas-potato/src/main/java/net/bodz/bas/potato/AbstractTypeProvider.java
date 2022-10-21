package net.bodz.bas.potato;

public abstract class AbstractTypeProvider
        implements
            ITypeProvider {

    private final int infoset;

    public AbstractTypeProvider(int infoset) {
        this.infoset = infoset;
    }

    public AbstractTypeProvider(int withInfo, int withoutInfo) {
        int infoset = getDefaultInfoset();
        infoset |= withInfo;
        infoset &= ~withoutInfo;
        this.infoset = infoset;
    }

    @Override
    public int getPriority() {
        return 0;
    }

}

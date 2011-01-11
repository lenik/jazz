package net.bodz.bas.flow;

public abstract class AbstractPortMeta
        implements IPortMeta {

    private final String name;

    public AbstractPortMeta(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

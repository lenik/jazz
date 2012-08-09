package net.bodz.bas.flow.unit.metadata;


public abstract class AbstractPortMetadata
        implements IPortMeta {

    private final String name;

    public AbstractPortMetadata(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

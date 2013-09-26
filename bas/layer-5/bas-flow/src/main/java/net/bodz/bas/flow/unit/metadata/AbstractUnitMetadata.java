package net.bodz.bas.flow.unit.metadata;

public class AbstractUnitMetadata
        implements IUnitMetadata {

    private final String name;

    public AbstractUnitMetadata(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

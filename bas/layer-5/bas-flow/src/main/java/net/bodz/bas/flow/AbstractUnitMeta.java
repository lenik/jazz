package net.bodz.bas.flow;

public class AbstractUnitMeta
        implements IUnitMeta {

    private final String name;

    public AbstractUnitMeta(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

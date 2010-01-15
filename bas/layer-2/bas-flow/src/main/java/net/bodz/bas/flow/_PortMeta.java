package net.bodz.bas.flow;

public abstract class _PortMeta implements PortMeta {

    private final String name;

    public _PortMeta(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

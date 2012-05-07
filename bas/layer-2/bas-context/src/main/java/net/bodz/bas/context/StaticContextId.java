package net.bodz.bas.context;

public class StaticContextId
        extends AbstractContextId {

    StaticContextId() {
        super("static");
    }

    @Override
    public IContextId getParentContextId() {
        return null;
    }

    static final StaticContextId instance = new StaticContextId();

    public static StaticContextId getInstance() {
        return instance;
    }

}

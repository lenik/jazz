package net.bodz.bas.arch.context;

public class StaticContext
        extends AbstractContext {

    StaticContext() {
        super("static");
    }

    @Override
    public IContext getParentContext() {
        return null;
    }

    static final StaticContext instance = new StaticContext();

    public static StaticContext getInstance() {
        return instance;
    }

}

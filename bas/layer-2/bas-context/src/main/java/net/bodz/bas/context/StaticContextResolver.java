package net.bodz.bas.context;

public class StaticContextResolver
        extends AbstractContextResolver {

    IContext staticContext;

    public StaticContextResolver(IContext staticContext) {
        this.staticContext = staticContext;
    }

    @Override
    public IContext resolve(IContext fallback) {
        return staticContext;
    }

    static StaticContextResolver instance = new StaticContextResolver(StaticContext.getInstance());

    public static StaticContextResolver getInstance() {
        return instance;
    }

}

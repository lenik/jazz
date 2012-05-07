package net.bodz.bas.context;

public class StaticContextResolver
        extends AbstractContextResolver {

    IContextId staticContext;

    public StaticContextResolver(IContextId staticContext) {
        this.staticContext = staticContext;
    }

    @Override
    public IContextId resolve(IContextId fallback) {
        return staticContext;
    }

    static StaticContextResolver instance = new StaticContextResolver(StaticContextId.getInstance());

    public static StaticContextResolver getInstance() {
        return instance;
    }

}

package net.bodz.bas.context;

public abstract class AbstractContextResolver
        implements IContextResolver {

    @Override
    public IContext resolve() {
        return resolve(StaticContext.getInstance());
    }

}

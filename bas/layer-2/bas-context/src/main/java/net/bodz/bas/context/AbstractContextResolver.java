package net.bodz.bas.context;

public abstract class AbstractContextResolver
        implements IContextResolver {

    @Override
    public IContextId resolve() {
        return resolve(StaticContextId.getInstance());
    }

}

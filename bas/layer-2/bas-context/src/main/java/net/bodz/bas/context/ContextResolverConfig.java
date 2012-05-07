package net.bodz.bas.context;

/**
 * This class isn't a {@link IContextId} at all. It only servs to get the default context, which is
 * configurable using the default context rule.
 */
public class ContextResolverConfig {

    static IContextResolver defaultContextResolver;

    static {
        defaultContextResolver = CurrentThreadContextResolver.getInstance();
    }

    public static IContextResolver getDefaultContextResolver() {
        return defaultContextResolver;
    }

    public static void setDefaultContextResolver(IContextResolver globalContextResolver) {
        if (globalContextResolver == null)
            throw new NullPointerException("defaultContextResolver");
        ContextResolverConfig.defaultContextResolver = globalContextResolver;
    }

}

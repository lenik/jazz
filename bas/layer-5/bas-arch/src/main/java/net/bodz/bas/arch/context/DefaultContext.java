package net.bodz.bas.arch.context;

import net.bodz.bas.closure.alt.Func0;

/**
 * This class isn't a {@link IContext} at all. It only servs to get the default context, which is
 * configurable using the default context rule.
 */
public class DefaultContext {

    private DefaultContext() {
    }

    private static Func0<? extends IContext> defaultContextRule = DefaultDefaultContextRule.instance;

    private static class DefaultDefaultContextRule
            implements Func0<IContext> {

        @Override
        public IContext eval() {
            return ThreadContext.getCurrentThreadContext();
        }

        static final DefaultDefaultContextRule instance = new DefaultDefaultContextRule();

    }

    /**
     * Get the default context rule.
     * <p>
     * The determination of the default context is deferred until the default context rule is
     * evaluated.
     * 
     * @return Non-<code>null</code> functor which evaluates to a context identity.
     */
    public static Func0<? extends IContext> getDefaultContextRule() {
        return defaultContextRule;
    }

    /**
     * Set the default context rule.
     * <p>
     * The determination of the default context is deferred until the default context rule is
     * evaluated.
     * 
     * @return Non-<code>null</code> functor which evaluates to a context identity.
     */
    public static void setDefaultContextRule(Func0<? extends IContext> contextRule) {
        if (contextRule == null)
            throw new NullPointerException("defaultContextRule");
        defaultContextRule = contextRule;
    }

    /**
     * Get the default context.
     * <p>
     * The default context is determined by the function default context rule.
     * 
     * @return Non-<code>null</code> default context.
     * @see #getDefaultContextRule()
     * @see #setDefaultContextRule(Func0)
     */
    public static IContext getInstance() {
        return defaultContextRule.eval();
    }

}

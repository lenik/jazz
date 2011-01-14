package net.bodz.bas.context;

/**
 * The context abstraction.
 * <p>
 * This class defines what a context identity is.
 * 
 * A context identity is used to distinguish one context from another, and describes the context
 * hierachy by inheritance and transient.
 */
public interface IContext {

    /**
     * A descriptive context name.
     * <p>
     * Currently, the context name isn't used at all.
     * 
     * @return Non-<code>null</code> context name.
     */
    String getContextName();

    /**
     * Get the parent context.
     * 
     * A context should implicit inherit the status from its parent context, this may include the
     * environ variables, locale configurations, preferences and settings, etc.
     * 
     * @return Parent context, <code>null</code> if this is a root context.
     */
    IContext getParentContext();

    /**
     * Transient context is kind of "read-only". By affecting a transient context, indeed, the
     * nearest ancestor context is affected.
     * <p>
     * In the opposite, a context is not transient is concrete.
     * 
     * @return <code>true</code> If this is a transient context.
     */
    boolean isTransient();

}

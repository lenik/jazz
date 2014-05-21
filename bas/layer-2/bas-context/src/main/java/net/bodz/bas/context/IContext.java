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
    String getName();

    Object getIdentity();

    /**
     * Get the parent context.
     * 
     * A context should implicit inherit the status from its parent context, this may include the
     * environ variables, locale configurations, preferences and settings, etc.
     * 
     * @return Parent context, <code>null</code> if this is a root context.
     */
    IContext getParent();

    IContext getRoot();

    /**
     * Transparent context is kind of "read-only". By affecting a transparent context, indeed, the
     * nearest ancestor context is affected.
     * <p>
     * In the opposite, a context is not transparent is concrete.
     * 
     * @return <code>true</code> If this is a transparent context.
     */
    boolean isTransparent();

    /**
     * Test if a variable exists.
     */
    boolean contains(String name);

    /**
     * Get value of a specific variable.
     */
    Object get(String name);

    /**
     * Set the variable's value.
     */
    void set(String name, Object value);

    MutableContext DEFAULT = new MutableContext("default", new Object());

}

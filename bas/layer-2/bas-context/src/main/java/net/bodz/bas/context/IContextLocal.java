package net.bodz.bas.context;

import net.bodz.bas.t.ref.Ref;

public interface IContextLocal<T>
        extends Ref<T> {

    IContext getCurrentContext();

    String getName();

    /**
     * Return value in the given context, or the nearest ancestor context if it's not defined.
     * 
     * @param context
     *            Non-<code>null</code> context.
     * @return {@link #getDefault() default} value if the value isn't defined in the context chain.
     */
    T get(IContext context);

    /**
     * Set value in the given context, or the nearest ancestor context if it's transient.
     * 
     * @param context
     *            The context to be affected, all transient contexts in the context chain are
     *            skipped.
     */
    void set(IContext context, T value);

    void remove(IContext context);

    /**
     * Shortcut for: <code>get(getCurrentContext())</code>.
     */
    @Override
    T get();

    /**
     * Shortcut for: <code>set(getCurrentContext(), value)</code>.
     */
    @Override
    void set(T value);

    /**
     * Shortcut for: <code>remove(getCurrentContext())</code>.
     */
    @Override
    void remove();

}

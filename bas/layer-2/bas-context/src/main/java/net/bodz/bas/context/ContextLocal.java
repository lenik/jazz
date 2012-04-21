package net.bodz.bas.context;

import java.util.HashMap;
import java.util.Map;

/**
 * The context local class provides an inheritable variable management. If the variable is allocated
 * in one context, it will be also the default variable storage for all its children contexts unless
 * it's overrided.
 * <p>
 * This is a little bit similar to the {@link ThreadLocal}. While {@link Thread} isn't hierachical,
 * (maybe a Thread belongs to a {@link ThreadGroup}, that's all. You won't go any further beyond the
 * ThreadGroup.) Imaging there is a parent thread, however, ThreadLocal won't treat the parent
 * thread any different. If the variable is allocated in the parent thread, it won't be accessible
 * in the child thread.
 * 
 * (See {@link ThreadContext} and {@link ThreadGroupContext} to get the idea how to make a
 * ThreadGroup be <i>"parent"</i> of a Thread.)
 */
public class ContextLocal<T> {

    private T root;
    private Map<IContext, T> map;

    public ContextLocal() {
        this.map = new HashMap<IContext, T>();
    }

    public ContextLocal(T root) {
        this();
        this.root = root;
    }

    // protected abstract T create(T valueInParentContext)
    // throws CreateException;

    public T getRoot() {
        return root;
    }

    public void setRoot(T root) {
        this.root = root;
    }

    /**
     * The max inheritance depth. If there are more then this many parents in the context, exception
     * may be raised. This is generally caused by dead loop.
     */
    public static final int maxDepth = 128;

    /**
     * The max transient depth. If there are more then this many parents to pass through, exception
     * may be raised. This is generally caused by dead loop.
     */
    public static final int maxTransientDepth = 16;

    /**
     * Return value in the given context, or the nearest ancestor context if it's not defined.
     * 
     * @param context
     *            Non-<code>null</code> context.
     * @return {@link #getDefault() default} value if the value isn't defined in the context chain.
     */
    public T get(IContext context) {
        IContext ancestor = context;
        int depth = 0;
        while (ancestor != null) {
            T value = map.get(ancestor);
            if (value != null || map.containsKey(ancestor))
                return value;
            if (++depth > maxDepth)
                throw new ContextOverflowException("Context too deep");
            ancestor = ancestor.getParentContext();
        }
        // getOrCreate()?
        return getRoot();
    }

    /**
     * Set value in the given context, or the nearest ancestor context if it's transient.
     * 
     * @param context
     *            The context to be affected, all transient contexts in the context chain are
     *            skipped.
     */
    public void set(IContext context, T value) {
        IContext concreteContext = context;
        int depth = 0;
        while (concreteContext.isTransient()) {
            concreteContext = concreteContext.getParentContext();
            if (concreteContext == null)
                // throw new IllegalUsageException("No concrete context, transient to death.");
                break;
            if (++depth > maxTransientDepth)
                throw new ContextOverflowException("Transient too deep");
        }

        map.put(concreteContext, value);
    }

    /**
     * A shortcut to get the value in the default context.
     * 
     * @return Context thread local value.
     * @def get(DefaultContext.getInstance())
     * @see ContextResolverConfig
     */
    public T get() {
        IContext context = ContextResolverConfig.defaultContextResolver.resolve();
        return get(context);
    }

    /**
     * A shortcut to set the value in the default context.
     * 
     * @def set(DefaultContext.getInstance(), value)
     * @see ContextResolverConfig
     */
    public void set(T value) {
        IContext context = getDefaultContext();
        set(context, value);
    }

    /**
     * A shortcut to get the value in the context of the given class.
     * 
     * @param clazz
     *            Non-<code>null</code> context class.
     * @return Context class local value.
     * @def get(ClassContext.getInstance(clazz))
     */
    public T get(Class<?> clazz) {
        return get(ClassContext.getInstance(clazz));
    }

    /**
     * A shortcut to set the value in the context of the given class.
     * 
     * @param clazz
     *            Non-<code>null</code> context class.
     * @def set(ClassContext.getInstance(clazz), value)
     */
    public void set(Class<?> clazz, T value) {
        set(ClassContext.getInstance(clazz), value);
    }

    protected IContext getDefaultContext() {
        IContext defaultContext = ContextResolverConfig.defaultContextResolver.resolve();
        return defaultContext;
    }

}

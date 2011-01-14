package net.bodz.bas.context;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.util.exception.IllegalUsageError;
import net.bodz.bas.util.exception.IllegalUsageException;

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

    private T defaultValue;
    private Map<IContext, T> map;

    public ContextLocal() {
        this.map = new HashMap<IContext, T>();
    }

    public ContextLocal(T defaultValue) {
        this();
        this.defaultValue = defaultValue;
    }

    // protected abstract T create(T valueInParentContext)
    // throws CreateException;

    protected T getDefault() {
        return defaultValue;
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
     * @throws NullPointerException
     *             If <code>context</code> is <code>null</code>.
     */
    public T get(IContext context) {
        if (context == null)
            throw new NullPointerException("context");
        IContext ancestor = context;
        int depth = 0;
        while (depth++ < maxDepth) {
            T value = map.get(ancestor);
            if (value != null || map.containsKey(ancestor))
                return value;
            ancestor = ancestor.getParentContext();
            if (ancestor == null)
                // getOrCreate()?
                return getDefault();
        }
        throw new IllegalUsageError("Context too deep");
    }

    /**
     * Set value in the given context, or the nearest ancestor context if it's transient.
     * 
     * @param context
     *            The context to be affected, all transient contexts in the context chain are
     *            skipped.
     * @throws NullPointerException
     *             If <code>context</code> is <code>null</code>.
     */
    public void set(IContext context, T value) {
        if (context == null)
            throw new NullPointerException("context");

        IContext concreteContext = context;
        int depth = 0;
        while (concreteContext.isTransient()) {
            concreteContext = concreteContext.getParentContext();
            if (concreteContext == null)
                throw new IllegalUsageException("No concrete context, transient to death.");
            if (++depth > maxTransientDepth)
                throw new IllegalUsageError("Transient too deep");
        }

        map.put(concreteContext, value);
    }

    /**
     * A shortcut to get the value in the default context.
     * 
     * @return Context thread local value.
     * @def get(DefaultContext.getInstance())
     * @see DefaultContext
     */
    public T get() {
        return get(DefaultContext.getInstance());
    }

    /**
     * A shortcut to set the value in the default context.
     * 
     * @def set(DefaultContext.getInstance(), value)
     * @see DefaultContext
     */
    public void set(T value) {
        set(DefaultContext.getInstance(), value);
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

}

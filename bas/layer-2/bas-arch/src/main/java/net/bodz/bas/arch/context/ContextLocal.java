package net.bodz.bas.arch.context;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.exceptions.IllegalUsageException;

/**
 * @test {@link ContextLocalTest}
 */
public class ContextLocal<T> {

    private Map<IContext, T> map;

    public ContextLocal() {
        this.map = new HashMap<IContext, T>();
    }

    // protected abstract T create(T valueInParentContext)
    // throws CreateException;

    protected T getDefault() {
        return null;
    }

    public static final int maxDepth = 1024;
    public static final int maxPenetratingDepth = 64;

    /**
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
     * @param context
     *            The context to be affected, all penetration context in the context chain is
     *            skipped.
     * @throws NullPointerException
     *             If <code>context</code> is <code>null</code>.
     */
    public void set(IContext context, T value) {
        if (context == null)
            throw new NullPointerException("context");

        IContext concreteContext = context;
        int depth = 0;
        while (concreteContext.isPenetrated()) {
            concreteContext = concreteContext.getParentContext();
            if (concreteContext == null)
                throw new IllegalUsageException("No concrete context, penetrated to death.");
            if (++depth > maxPenetratingDepth)
                throw new IllegalUsageError("Penetration too deep");
        }

        map.put(concreteContext, value);
    }

}

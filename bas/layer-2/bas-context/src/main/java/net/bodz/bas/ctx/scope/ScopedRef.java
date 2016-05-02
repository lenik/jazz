package net.bodz.bas.ctx.scope;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.DeadLoopException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

/**
 * Focused on the scope-tree.
 */
@ExcludedFromIndex
public class ScopedRef<T>
        implements IScopedRef<T> {

    /**
     * The max inheritance depth. If there are more then this many parents in the scope, exception
     * may be raised. This is generally caused by dead loop.
     */
    public static final int maxDepth = 128;

    /**
     * The max transient depth. If there are more then this many parents to pass through, exception
     * may be raised. This is generally caused by dead loop.
     */
    public static final int maxTransientDepth = 16;

    static IScopeTeller DEFAULT_TELLER = new IndirectScopeTeller();

    private Class<T> valueType;
    private Map<IScopeInstance, T> map = new HashMap<IScopeInstance, T>();
    private IScopeTeller teller = DEFAULT_TELLER;

    public ScopedRef(Class<T> valueType) {
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;
    }

    @Override
    public IScopeInstance getCurrentScope() {
        return teller.tell();
    }

    public IScopeTeller getTeller() {
        return teller;
    }

    public void setTeller(IScopeTeller teller) {
        this.teller = teller;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    public T getDefaultValue() {
        return null;
    }

    @Override
    public final T get() {
        return get(getCurrentScope());
    }

    @Override
    public final void set(T value) {
        set(getCurrentScope(), value);
    }

    @Override
    public final void remove() {
        remove(getCurrentScope());
    }

    @Override
    public T get(IScopeInstance scope) {
        String varName = getName();

        int depth = 0;
        while (scope != null) {
            if (scope.contains(varName))
                return (T) scope.get(varName);

            T value = map.get(scope);
            if (value != null || map.containsKey(scope))
                return value;

            if (++depth > maxDepth)
                throw new DeadLoopException("invalid scope hierarchy, depth overflow");
            scope = scope.getParent();
        }
        // getOrCreate()?
        return getDefaultValue();
    }

    /**
     * @throws DeadLoopException
     *             If transparent depth overflow.
     */
    @Override
    public void set(IScopeInstance scope, T value) {
        IScopeInstance concreteContext = scope;
        int depth = 0;
        while (concreteContext.isTransparent()) {
            concreteContext = concreteContext.getParent();
            if (concreteContext == null)
                // throw new IllegalUsageException("No concrete scope, transient to death.");
                break;
            if (++depth > maxTransientDepth)
                throw new DeadLoopException("transparent depth overflow");
        }

        map.put(concreteContext, value);
    }

    @Override
    public void remove(IScopeInstance scope) {
    }

}

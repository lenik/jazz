package net.bodz.bas.ctx.scope;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

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
    private Map<IScopeToken, T> map = new HashMap<IScopeToken, T>();
    private IScopeTeller teller = DEFAULT_TELLER;

    public ScopedRef(Class<T> valueType) {
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;
    }

    @Override
    public IScopeToken getCurrentScope() {
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
    public T get(IScopeToken scope) {
        String varName = getName();

        int depth = 0;
        while (scope != null) {
            if (scope.contains(varName))
                return (T) scope.get(varName);

            T value = map.get(scope);
            if (value != null || map.containsKey(scope))
                return value;

            if (++depth > maxDepth)
                throw new ContextOverflowException("Context too deep");
            scope = scope.getParent();
        }
        // getOrCreate()?
        return getDefaultValue();
    }

    @Override
    public void set(IScopeToken scope, T value) {
        IScopeToken concreteContext = scope;
        int depth = 0;
        while (concreteContext.isTransparent()) {
            concreteContext = concreteContext.getParent();
            if (concreteContext == null)
                // throw new IllegalUsageException("No concrete scope, transient to death.");
                break;
            if (++depth > maxTransientDepth)
                throw new ContextOverflowException("Transient too deep");
        }

        map.put(concreteContext, value);
    }

    @Override
    public void remove(IScopeToken scope) {
    }

}

package net.bodz.bas.context;

import java.util.HashMap;
import java.util.Map;

public class ContextLocal<T>
        implements IContextLocal<T> {

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

    static IContextTeller DEFAULT_TELLER = new IndirectContextTeller();

    private Class<T> valueType;
    private Map<IContext, T> map = new HashMap<IContext, T>();
    private IContextTeller teller = DEFAULT_TELLER;

    public ContextLocal(Class<T> valueType) {
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;
    }

    @Override
    public IContext getCurrentContext() {
        return teller.tell();
    }

    public IContextTeller getTeller() {
        return teller;
    }

    public void setTeller(IContextTeller teller) {
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
        return get(getCurrentContext());
    }

    @Override
    public final void set(T value) {
        set(getCurrentContext(), value);
    }

    @Override
    public final void remove() {
        remove(getCurrentContext());
    }

    @Override
    public T get(IContext context) {
        String varName = getName();

        int depth = 0;
        while (context != null) {
            if (context.contains(varName))
                return (T) context.get(varName);

            T value = map.get(context);
            if (value != null || map.containsKey(context))
                return value;

            if (++depth > maxDepth)
                throw new ContextOverflowException("Context too deep");
            context = context.getParent();
        }
        // getOrCreate()?
        return getDefaultValue();
    }

    @Override
    public void set(IContext context, T value) {
        IContext concreteContext = context;
        int depth = 0;
        while (concreteContext.isTransparent()) {
            concreteContext = concreteContext.getParent();
            if (concreteContext == null)
                // throw new IllegalUsageException("No concrete context, transient to death.");
                break;
            if (++depth > maxTransientDepth)
                throw new ContextOverflowException("Transient too deep");
        }

        map.put(concreteContext, value);
    }

    @Override
    public void remove(IContext context) {
    }

}

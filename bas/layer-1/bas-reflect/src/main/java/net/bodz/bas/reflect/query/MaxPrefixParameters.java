package net.bodz.bas.reflect.query;

import java.util.Arrays;

public class MaxPrefixParameters
        implements IParametersPredicate {

    private final Class<?>[] maxPrefix;
    private final IParametersPredicate next;

    public MaxPrefixParameters(Class<?>[] maxPrefix) {
        this(maxPrefix, null);
    }

    public MaxPrefixParameters(Class<?>[] maxPrefix, IParametersPredicate next) {
        if (maxPrefix == null)
            throw new NullPointerException("maxPrefix");
        this.maxPrefix = maxPrefix;
        this.next = next;
    }

    @Override
    public IParametersPredicate next() {
        return next;
    }

    @Override
    public int getParameterCount() {
        return maxPrefix.length;
    }

    @Override
    public boolean test(Class<?>[] parameterTypes) {
        if (parameterTypes.length < maxPrefix.length)
            return false;
        for (int i = 0; i < maxPrefix.length; i++) {
            Class<?> max = maxPrefix[i];
            if (max == null)
                continue;
            Class<?> actual = parameterTypes[i];
            if (actual == null)
                continue;
            if (!actual.isAssignableFrom(max))
                return false;
        }
        if (next != null) {
            int pc = getParameterCount();
            Class<?>[] chopped = Arrays.copyOfRange(parameterTypes, 0, parameterTypes.length - pc);
            if (!next.test(chopped))
                return false;
        }
        return true;
    }

}

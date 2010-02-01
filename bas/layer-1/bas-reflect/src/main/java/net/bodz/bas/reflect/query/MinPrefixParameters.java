package net.bodz.bas.reflect.query;

import java.util.Arrays;

public class MinPrefixParameters
        implements IParametersPredicate {

    private final Class<?>[] minPrefix;
    private final IParametersPredicate next;

    public MinPrefixParameters(Class<?>[] minPrefix) {
        this(minPrefix, null);
    }

    public MinPrefixParameters(Class<?>[] minPrefix, IParametersPredicate next) {
        if (minPrefix == null)
            throw new NullPointerException("minPrefix");
        this.minPrefix = minPrefix;
        this.next = next;
    }

    @Override
    public IParametersPredicate next() {
        return next;
    }

    @Override
    public int getParameterCount() {
        return minPrefix.length;
    }

    @Override
    public boolean test(Class<?>[] parameterTypes) {
        if (parameterTypes.length < minPrefix.length)
            return false;
        for (int i = 0; i < minPrefix.length; i++) {
            Class<?> min = minPrefix[i];
            if (min == null)
                continue;
            Class<?> actual = parameterTypes[i];
            if (actual == null)
                continue;
            if (!min.isAssignableFrom(actual))
                return false;
        }
        if (next != null) {
            int pc = getParameterCount();
            Class<?>[] chopped = Arrays.copyOfRange(parameterTypes, pc, parameterTypes.length - pc);
            if (!next.test(chopped))
                return false;
        }
        return true;
    }

}

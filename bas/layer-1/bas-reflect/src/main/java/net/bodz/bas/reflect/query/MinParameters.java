package net.bodz.bas.reflect.query;

import java.util.Arrays;

public class MinParameters
        implements IParametersPredicate {

    private final Class<?>[] expected;
    private final boolean matchPrefixOnly;
    private final IParametersPredicate next;

    public MinParameters(Class<?>[] expected, boolean matchPrefixOnly) {
        this(expected, matchPrefixOnly, null);
    }

    public MinParameters(Class<?>[] expected, boolean matchPrefixOnly, IParametersPredicate next) {
        if (expected == null)
            throw new NullPointerException("expected");
        this.expected = expected;
        this.matchPrefixOnly = matchPrefixOnly;
        this.next = next;
    }

    @Override
    public IParametersPredicate next() {
        return next;
    }

    @Override
    public int getParameterCount() {
        return expected.length;
    }

    @Override
    public boolean test(Class<?>[] parameterTypes) {
        if (parameterTypes.length < expected.length)
            return false;
        if (matchPrefixOnly || parameterTypes.length != expected.length)
            return false;
        for (int i = 0; i < expected.length; i++) {
            Class<?> min = expected[i];
            if (min == null)
                continue;
            Class<?> actual = parameterTypes[i];
            if (actual == null)
                continue;
            if (!min.isAssignableFrom(actual))
                return false;
        }
        if (next != null) {
            if (matchPrefixOnly) {
                int pc = getParameterCount();
                parameterTypes = Arrays.copyOfRange(parameterTypes, pc, parameterTypes.length - pc);
            }
            if (!next.test(parameterTypes))
                return false;
        }
        return true;
    }

}

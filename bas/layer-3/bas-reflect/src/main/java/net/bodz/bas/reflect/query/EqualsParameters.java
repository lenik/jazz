package net.bodz.bas.reflect.query;

import java.util.Arrays;

import net.bodz.bas.lang.Nullables;

public class EqualsParameters
        implements IParametersPredicate {

    private final Class<?>[] expected;
    private final boolean matchPrefixOnly;
    private final IParametersPredicate next;

    public EqualsParameters(Class<?>[] prefix, boolean matchPrefixOnly) {
        this(prefix, matchPrefixOnly, null);
    }

    public EqualsParameters(Class<?>[] expected, boolean matchPrefixOnly, IParametersPredicate next) {
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
            if (!Nullables.equals(expected[i], parameterTypes[i]))
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

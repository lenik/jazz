package net.bodz.bas.reflect.query;

import java.util.Arrays;

import net.bodz.bas.lang.Nullables;

public class PrefixParameters
        implements IParametersPredicate {

    private final Class<?>[] prefix;
    private final IParametersPredicate next;

    public PrefixParameters(Class<?>[] prefix) {
        this(prefix, null);
    }

    public PrefixParameters(Class<?>[] prefix, IParametersPredicate next) {
        if (prefix == null)
            throw new NullPointerException("prefix");
        this.prefix = prefix;
        this.next = next;
    }

    @Override
    public IParametersPredicate next() {
        return next;
    }

    @Override
    public int getParameterCount() {
        return prefix.length;
    }

    @Override
    public boolean test(Class<?>[] parameterTypes) {
        if (parameterTypes.length < prefix.length)
            return false;
        for (int i = 0; i < prefix.length; i++) {
            if (!Nullables.equals(prefix[i], parameterTypes[i]))
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

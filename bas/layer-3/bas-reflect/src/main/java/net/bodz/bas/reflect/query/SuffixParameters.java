package net.bodz.bas.reflect.query;

import java.util.Arrays;

import net.bodz.bas.util.Nullables;

public class SuffixParameters
        implements IParametersPredicate {

    private final Class<?>[] suffix;
    private final IParametersPredicate next;

    public SuffixParameters(Class<?>[] suffix) {
        this(suffix, null);
    }

    public SuffixParameters(Class<?>[] suffix, IParametersPredicate next) {
        if (suffix == null)
            throw new NullPointerException("suffix");
        this.suffix = suffix;
        this.next = next;
    }

    @Override
    public IParametersPredicate next() {
        return next;
    }

    @Override
    public int getParameterCount() {
        return suffix.length;
    }

    @Override
    public boolean test(Class<?>[] parameterTypes) {
        int offset = parameterTypes.length - suffix.length;
        if (offset < 0)
            return false;
        for (int i = 0; i < suffix.length; i++) {
            if (!Nullables.equals(suffix[i], parameterTypes[offset + i]))
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

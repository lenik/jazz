package net.bodz.bas.fn;

import java.lang.reflect.Array;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.meta.decl.NotNull;

public class SeqMatcher<T>
        implements Predicate<T> {

    final List<T> pattern;
    final T[] mem;
    int end;
    int len;

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public SeqMatcher(T... pattern) {
        this((Class<T>) pattern.getClass().getComponentType(), Arrays.asList(pattern));
    }

    @SuppressWarnings("unchecked")
    public SeqMatcher(@NotNull Class<T> cType, @NotNull List<T> pattern) {
        this.pattern = pattern;
        this.mem = (T[]) Array.newInstance(cType, pattern.size());
    }

    @Override
    public boolean test(T input) {
        if (mem.length == 0)
            return true;
        mem[end++] = input;
        if (len < mem.length) {
            len++;
            if (len < mem.length)
                return false;
        }

        end %= len;
        int pos = end;
        for (int i = 0; i < len; i++) {
            T ch = pattern.get(i);
            if (ch != mem[pos++])
                return false;
            pos %= len;
        }
        return true;
    }

    @SafeVarargs
    public static <T> boolean contains(T[] input, T... pattern) {
        return contains(Arrays.asList(input), pattern);
    }

    @SafeVarargs
    public static <T> boolean contains(Iterable<T> input, T... pattern) {
        SeqMatcher<T> matcher = new SeqMatcher<>(pattern);
        for (T el : input)
            if (matcher.test(el))
                return true;
        return false;
    }

    @SafeVarargs
    public static <T> int indexOf(T[] input, T... pattern) {
        return indexOf(Arrays.asList(input), pattern);
    }

    @SafeVarargs
    public static <T> int indexOf(Iterable<T> input, T... pattern) {
        SeqMatcher<T> matcher = new SeqMatcher<>(pattern);
        int pos = 0;
        for (T el : input) {
            if (matcher.test(el))
                return pos - pattern.length;
            pos++;
        }
        return -1;
    }

}

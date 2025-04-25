package net.bodz.bas.fn;

import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;

public class CharSeqMatcher
        implements CharPredicate
                    {

    final char[] pattern;
    final char[] mem;
    int end;
    int len;

    public CharSeqMatcher(String pattern) {
        this(pattern.toCharArray());
    }

    public CharSeqMatcher(@NotNull char... pattern) {
        this.pattern = pattern;
        this.mem = new char[pattern.length];
    }

    public void reset() {
        end = len = 0;
    }

    @Override
    public boolean test(char input) {
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
            char ch = pattern[i];
            if (ch != mem[pos++])
                return false;
            pos %= len;
        }
        return true;
    }

    public boolean test(@NotNull String input) {
        return test(input.toCharArray());
    }

    public boolean test(@NotNull char[] input) {
        return test(input, 0, input.length);
    }

    public boolean test(@NotNull char[] input, int off, int len) {
        for (int i = 0; i < len; i++)
            if (test(input[off++]))
                return true;
        return false;
    }

    public int search(@NotNull String input) {
        return search(input.toCharArray());
    }

    public int search(@NotNull char[] input) {
        return search(input, 0, input.length);
    }

    public int search(@NotNull char[] input, int off, int len) {
        for (int i = 0; i < len; i++)
            if (test(input[off++]))
                return off - pattern.length;
        return -1;
    }

    public static <T> boolean contains(String input, String pattern) {
        return new CharSeqMatcher(pattern).test(input);
    }

    public static <T> int indexOf(String input, String pattern) {
        return new CharSeqMatcher(pattern).search(input);
    }

}

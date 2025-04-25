package net.bodz.bas.fn;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import net.bodz.bas.meta.decl.NotNull;

public class ByteSeqMatcher
        implements BytePredicate {

    static Charset defaultCharset = StandardCharsets.ISO_8859_1;

    final byte[] pattern;
    final byte[] mem;
    int end;
    int len;

    public ByteSeqMatcher(String pattern) {
        this(pattern, defaultCharset);
    }

    public ByteSeqMatcher(String pattern, Charset charset) {
        this(pattern.getBytes(charset));
    }

    public ByteSeqMatcher(@NotNull byte... pattern) {
        this.pattern = pattern;
        this.mem = new byte[pattern.length];
    }

    public void reset() {
        end = len = 0;
    }

    public final boolean test(char t) {
        return test((byte) t);
    }

    @Override
    public boolean test(byte input) {
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
            byte ch = pattern[i];
            if (ch != mem[pos++])
                return false;
            pos %= len;
        }
        return true;
    }

    public boolean test(@NotNull String input) {
        return test(input, defaultCharset);
    }

    public boolean test(@NotNull String input, @NotNull Charset charset) {
        return test(input.getBytes(charset));
    }

    public boolean test(@NotNull byte[] input) {
        return test(input, 0, input.length);
    }

    public boolean test(@NotNull byte[] input, int off, int len) {
        for (int i = 0; i < len; i++)
            if (test(input[off++]))
                return true;
        return false;
    }

    public int search(@NotNull String input) {
        return search(input, defaultCharset);
    }

    public int search(@NotNull String input, @NotNull Charset charset) {
        return search(input.getBytes(charset));
    }

    public int search(@NotNull byte[] input) {
        return search(input, 0, input.length);
    }

    public int search(@NotNull byte[] input, int off, int len) {
        for (int i = 0; i < len; i++)
            if (test(input[off++]))
                return off - pattern.length;
        return -1;
    }

    public static <T> boolean contains(String input, String pattern) {
        return new ByteSeqMatcher(pattern).test(input);
    }

    public static <T> int indexOf(String input, String pattern) {
        return new ByteSeqMatcher(pattern).search(input);
    }

}

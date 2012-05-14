package net.bodz.bas.disp.util;

import java.util.Iterator;

import net.bodz.bas.util.Nullables;

public class SuffixTokens
        implements Iterable<String> {

    private final CharSequence string;
    private final boolean includeStartWord;

    public SuffixTokens(CharSequence string) {
        this(string, false);
    }

    public SuffixTokens(CharSequence string, boolean includeStartWord) {
        if (string == null)
            throw new NullPointerException("string");
        this.string = string;
        this.includeStartWord = includeStartWord;
    }

    @Override
    public Iterator<String> iterator() {
        return new SuffixTokenizer(string, includeStartWord);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SuffixTokens o = (SuffixTokens) obj;
        if (!Nullables.equals(string, o.string))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (string == null ? 0 : string.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (String suffix : this) {
            buf.append(suffix);
            buf.append('\n');
        }
        return buf.toString();
    }

}

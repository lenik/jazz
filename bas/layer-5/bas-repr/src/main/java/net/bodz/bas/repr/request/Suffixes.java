package net.bodz.bas.repr.request;

import java.util.Iterator;

public class Suffixes
        implements Iterable<String> {

    private final CharSequence string;
    private final boolean includeStartWord;

    public Suffixes(CharSequence string) {
        this(string, false);
    }

    public Suffixes(CharSequence string, boolean includeStartWord) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((string == null) ? 0 : string.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Suffixes other = (Suffixes) obj;
        if (string == null) {
            if (other.string != null)
                return false;
        } else if (!string.equals(other.string))
            return false;
        return true;
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

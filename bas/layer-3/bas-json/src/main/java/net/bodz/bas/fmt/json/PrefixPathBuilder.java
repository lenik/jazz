package net.bodz.bas.fmt.json;

public class PrefixPathBuilder {

    private final StringBuilder buf;
    private final int prefixLength;

    public PrefixPathBuilder(String prefix) {
        if (prefix != null) {
            this.buf = new StringBuilder(prefix);
            this.prefixLength = prefix.length();
        } else {
            this.buf = new StringBuilder();
            this.prefixLength = 0;
        }
    }

    void reset() {
        buf.delete(prefixLength, buf.length());
    }

    public synchronized String append(String s) {
        if (prefixLength == 0)
            return s;
        reset();
        buf.append('/');
        buf.append(s);
        return buf.toString();
    }

    public synchronized String append(int index) {
        reset();
        buf.append('[');
        buf.append(index);
        buf.append(']');
        return buf.toString();
    }

}

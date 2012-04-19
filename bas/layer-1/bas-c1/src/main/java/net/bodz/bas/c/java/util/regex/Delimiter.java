package net.bodz.bas.c.java.util.regex;

import java.io.Serializable;

public class Delimiter implements Serializable {

    private static final long serialVersionUID = 1766948183124423805L;

    private final String start;
    private final String end;

    public Delimiter(String delimiter) {
        if (delimiter == null)
            throw new NullPointerException("delimiter");
        start = end = delimiter;
    }

    public Delimiter(String start, String end) {
        if (start == null)
            throw new NullPointerException("start");
        if (end == null)
            throw new NullPointerException("end");
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public int hashCode() {
        int hash = start.hashCode();
        hash ^= 0x4dfa980d + end.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Delimiter))
            return false;
        Delimiter t = (Delimiter) obj;
        return start.equals(t.start) && end.equals(t.end);
    }

    @Override
    public String toString() {
        return start + "example" + end;
    }

}

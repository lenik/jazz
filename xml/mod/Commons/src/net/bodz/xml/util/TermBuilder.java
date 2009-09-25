package net.bodz.xml.util;

public class TermBuilder {

    private final TermDict dict;
    private StringBuffer   buf;

    public TermBuilder() {
        this(null);
    }

    public TermBuilder(TermDict dict) {
        this.dict = dict;
        buf = new StringBuffer();
    }

    public void reset() {
        buf.setLength(0);
    }

    public void put(int termId) {
        String term = dict.getTerm(termId);
        buf.append(term);
    }

    public void put(String term) {
        buf.append(term);
    }

    /**
     * @return <code>null</code> if empty string.
     */
    public String toString() {
        if (buf.length() == 0)
            return null;
        return buf.toString();
    }

}

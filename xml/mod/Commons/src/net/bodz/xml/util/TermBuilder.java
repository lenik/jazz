package net.bodz.xml.util;

import java.util.List;

public class TermBuilder {

    private final TermDict dict;
    private StringBuffer   buf;

    public TermBuilder(TermDict dict) {
        if (dict == null)
            throw new NullPointerException("dict");
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

    public void putIndex(int index) {
        buf.append(index);
    }

    public void putTypeParameter(String typeParameter) {
        buf.append('<');
        buf.append(typeParameter);
        buf.append('>');
    }

    public void putParameters(String rawParameters) {
        buf.append('(');
        buf.append(rawParameters);
        buf.append(')');
    }

    public void putParameters(List<String> parameters) {
        if (parameters == null || parameters.isEmpty())
            return;
        buf.append('(');
        boolean cont = false;
        for (String parameter : parameters) {
            if (cont)
                buf.append(", ");
            buf.append(parameter);
            cont = true;
        }
        buf.append(')');
    }

    public void putBounds(String rawBounds) {
        buf.append('[');
        buf.append(rawBounds);
        buf.append(']');
    }

    public void putBounds(int... bounds) {
        buf.append('[');
        for (int i = 0; i < bounds.length; i++) {
            if (i != 0)
                buf.append(",");
            int b = bounds[i];
            if (b != 0)
                buf.append(b);
        }
        buf.append(']');
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

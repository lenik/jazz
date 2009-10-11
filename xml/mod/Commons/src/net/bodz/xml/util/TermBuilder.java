package net.bodz.xml.util;

import java.util.ArrayList;
import java.util.List;

public class TermBuilder {

    private final TermDict dict;
    private StringBuffer   buf;

    private String         typeParameter;
    private List<String>   parameters;
    private int[]          bounds;

    public TermBuilder(TermDict dict) {
        if (dict == null)
            throw new NullPointerException("dict");
        this.dict = dict;
        buf = new StringBuffer();
        parameters = new ArrayList<String>();
    }

    public void reset() {
        buf.setLength(0);
    }

    void flush() {
        if (typeParameter != null) {
            buf.append('\u00AB'); // «
            buf.append(typeParameter);
            buf.append('\u00BB'); // »
            typeParameter = null;
        }
        if (parameters != null && !parameters.isEmpty()) {
            buf.append('(');
            boolean cont = false;
            for (String parameter : parameters) {
                if (cont)
                    buf.append(", ");
                buf.append(parameter);
                cont = true;
            }
            buf.append(')');
            parameters = null;
        }
        if (bounds != null) {
            buf.append('[');
            for (int i = 0; i < bounds.length; i++) {
                if (i != 0)
                    buf.append(",");
                int b = bounds[i];
                if (b != 0)
                    buf.append(b);
            }
            buf.append(']');
            bounds = null;
        }
    }

    public void put(int termId) {
        String term = dict.getTerm(termId);
        put(term);
    }

    public void put(String term) {
        flush();
        buf.append(term);
    }

    public void putIndex(int index) {
        buf.append(index);
    }

    public void putTypeParameter(String typeParameter) {
        if (this.typeParameter != null)
            throw new IllegalStateException("type parameter has already been set to: "
                    + this.typeParameter);
        this.typeParameter = typeParameter;
    }

    public void putParameter(String parameter) {
        this.parameters.add(parameter);
    }

    public void putParameters(String... parameters) {
        if (parameters != null)
            for (String s : parameters)
                this.parameters.add(s);
    }

    public void putParameters(List<String> parameters) {
        if (parameters != null)
            this.parameters.addAll(parameters);
    }

    public void putBounds(String rawBounds) {
        buf.append('[');
        buf.append(rawBounds);
        buf.append(']');
    }

    public void putBounds(int... bounds) {
        if (this.bounds != null)
            throw new IllegalStateException("Bounds has already been set to: " + this.bounds);
        this.bounds = bounds;
    }

    /**
     * @return <code>null</code> if empty string.
     */
    public String toString() {
        flush();
        if (buf.length() == 0)
            return null;
        return buf.toString();
    }

}

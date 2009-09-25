package net.bodz.xml.util;

import java.io.Serializable;

import net.bodz.bas.types.util.Strings;

/** Term(params)[bounds] */
public class Term implements Serializable {

    private static final long serialVersionUID = -1846722082543087995L;

    private final int         id;
    private final String      name;
    private final String[]    parameters;
    private final Integer[]   bounds;

    public Term(String name, String... params) {
        this(0, name, params);
    }

    public Term(int id, String name, String... params) {
        this.id = id;
        this.name = name;
        this.parameters = params;
        this.bounds = null;
    }

    public Term(int id, String name, String[] params, Integer[] bounds) {
        this.id = id;
        this.name = name;
        this.parameters = params;
        this.bounds = bounds;
    }

    public Term(int id, String name, String[] params, String[] _bounds) {
        this.id = id;
        this.name = name;
        this.parameters = params;
        if (_bounds == null)
            this.bounds = null;
        else {
            this.bounds = new Integer[_bounds.length];
            for (int i = 0; i < _bounds.length; i++) {
                String s = _bounds[i];
                if (s == null)
                    throw new NullPointerException("bound[" + i + "]");
                s = s.trim();
                if (s.isEmpty())
                    this.bounds[i] = null;
                else
                    this.bounds[i] = Integer.valueOf(s);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getParameters() {
        return parameters;
    }

    public int getParameterCount() {
        if (parameters == null)
            return 0;
        return parameters.length;
    }

    public String getRawParameter() {
        return Strings.join(" ", parameters);
    }

    public String getParameter(int index) {
        return parameters[index];
    }

    public Integer[] getBounds() {
        return bounds;
    }

    public int getDimension() {
        if (bounds == null)
            return 0;
        else
            return bounds.length;
    }

    public int getBound(int dimIndex) {
        if (bounds == null || dimIndex < 0 || dimIndex >= bounds.length)
            throw new IllegalArgumentException("Bad index: " + dimIndex);
        return bounds[dimIndex];
    }

    @Override
    public String toString() {
        if (parameters == null && bounds == null)
            return name;
        StringBuffer buffer = new StringBuffer(name);
        if (parameters != null) {
            buffer.append('(');
            buffer.append(Strings.join(",", parameters));
            buffer.append(')');
        }
        if (bounds != null) {
            buffer.append('[');
            for (int i = 0; i < bounds.length; i++) {
                if (i > 0)
                    buffer.append(',');
                if (bounds[i] != null)
                    buffer.append(bounds[i]);
            }
            buffer.append(']');
        }
        return buffer.toString();
    }

}

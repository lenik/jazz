package net.bodz.xml.util;

import java.io.Serializable;

import net.bodz.bas.types.util.Strings;

/**
 * <pre>
 * TermINDEX &lt; type &gt; (params)[bounds]
 * </pre>
 */
public class Term implements Serializable {

    private static final long serialVersionUID = -1846722082543087995L;

    private final int         id;
    private final String      name;
    private Integer           index;
    private String            typeParameter;
    private String            rawParameter;
    private String[]          parameters;
    private Integer[]         bounds;

    public Term(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Term(int id, String name, String... parameters) {
        this(id, name);
        this.parameters = parameters;
    }

    public Term(int id, String name, String[] parameters, Integer[] bounds) {
        this(id, name, parameters);
        setBounds(bounds);
    }

    public Term(int id, String name, String[] parameters, String[] bounds) {
        this(id, name, parameters);
        setBounds(bounds);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasIndex() {
        return index != null;
    }

    public int getIndex() {
        return getIndex(0);
    }

    public int getIndex(int defaultValue) {
        if (index == null)
            return defaultValue;
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTypeParameter() {
        return typeParameter;
    }

    public void setTypeParameter(String typeParameter) {
        this.typeParameter = typeParameter;
    }

    public String getRawParameter() {
        if (rawParameter != null)
            return rawParameter;
        if (parameters == null)
            return null;
        return Strings.join(", ", parameters);
    }

    public void setRawParameter(String rawParameter) {
        this.rawParameter = rawParameter;
    }

    public int getParameterCount() {
        if (parameters == null)
            return 0;
        return parameters.length;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String get(int parameterIndex) {
        return parameters[parameterIndex];
    }

    public int getInt(int parameterIndex) {
        String s = parameters[parameterIndex];
        return Integer.parseInt(s);
    }

    public int getInt(int parameterIndex, int defaultValue) {
        try {
            return getInt(parameterIndex);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(int parameterIndex) {
        String s = parameters[parameterIndex];
        if ("1".equals(s))
            return true;
        return Boolean.parseBoolean(s);
    }

    public void set(int parameterIndex, Object parameter) {
        parameters[parameterIndex] = String.valueOf(parameter);
    }

    public Integer[] getBounds() {
        return bounds;
    }

    public void setBounds(Integer[] bounds) {
        if (bounds == null)
            throw new NullPointerException("bounds");
        this.bounds = bounds;
    }

    public void setBounds(String[] bounds) {
        if (bounds == null)
            throw new NullPointerException("bounds");
        this.bounds = new Integer[bounds.length];
        for (int i = 0; i < bounds.length; i++) {
            String s = bounds[i];
            if (s == null)
                throw new NullPointerException("bound[" + i + "]");
            s = s.trim();
            if (s.isEmpty())
                this.bounds[i] = null;
            else
                this.bounds[i] = Integer.valueOf(s);
        }
    }

    public int getDimension() {
        if (bounds == null)
            return 0;
        else
            return bounds.length;
    }

    public int getBound(int dim) {
        return getBound(dim, 0);
    }

    public int getBound(int dim, int defaultValue) {
        if (bounds == null || dim < 0 || dim >= bounds.length)
            throw new IllegalArgumentException("Bad index: " + dim);
        Integer bound = bounds[dim];
        if (bound == null)
            return defaultValue;
        return bound;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer(name);
        if (index != null)
            buffer.append(index);
        if (typeParameter != null) {
            buffer.append('\u00AB'); // // «
            buffer.append(typeParameter);
            buffer.append('\u00BB'); // »
        }
        String rp = getRawParameter();
        if (rp != null) {
            buffer.append('(');
            buffer.append(rp);
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

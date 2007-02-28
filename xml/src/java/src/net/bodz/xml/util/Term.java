package net.bodz.xml.util;

import java.io.Serializable;

import net.sf.freejava.util.Objects;
import net.sf.freejava.util.Strings;

/** Term(param)[dims] */
public class Term implements Serializable {
    
    private static final long serialVersionUID = -1846722082543087995L;
    
    protected String    name;
    protected String[]  params;
    protected Integer[] dims;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] strings) {
        if (strings == null || strings.length == 0)
            params = null;
        else
            params = strings;
    }

    public int getParamCount() {
        if (params == null)
            return 0;
        return params.length;
    }

    public String getParam(int index) {
        Objects.checkIndex(params, index);
        return params[index];
    }

    public void setParam(int index, String param) {
        Objects.checkIndex(params, index);
        params[index] = param;
    }

    public Integer[] getDims() {
        return dims;
    }

    public void setDims(Integer[] dims) {
        this.dims = dims;
    }

    public void setDims(String[] strings) {
        if (strings == null || strings.length == 0)
            dims = null;
        else {
            dims = new Integer[strings.length];
            for (int i = 0; i < strings.length; i++) {
                String s = strings[i];
                if (s != null && s.trim().length() > 0)
                    dims[i] = Integer.valueOf(s);
            }
        }
    }

    public int getDimCount() {
        if (dims == null)
            return 0;
        return dims.length;
    }

    public Integer getDim(int index) {
        Objects.checkIndex(dims, index);
        return dims[index];
    }

    public void setDim(int index, Integer dim) {
        Objects.checkIndex(dims, index);
        dims[index] = dim;
    }

    @Override
    public String toString() {
        if (params == null && dims == null)
            return name;
        StringBuffer buffer = new StringBuffer(name);
        if (params != null) {
            buffer.append('(');
            buffer.append(Strings.join(",", params));
            buffer.append(')');
        }
        if (dims != null) {
            buffer.append('[');
            for (int i = 0; i < dims.length; i++) {
                if (i > 0)
                    buffer.append(',');
                if (dims[i] != null)
                    buffer.append(dims[i]);
            }
            buffer.append(']');
        }
        return buffer.toString();
    }
    
}

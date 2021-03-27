package net.bodz.bas.fmt.api;

import java.io.Serializable;

public class MutableTextAttribute
        implements ITextAttribute, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String data;

    public MutableTextAttribute(String name, String data) {
        if (name == null)
            throw new NullPointerException("name");
        if (data == null)
            throw new NullPointerException("data");
        this.name = name;
        this.data = data;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data == null)
            throw new NullPointerException("data");
        this.data = data;
    }

}

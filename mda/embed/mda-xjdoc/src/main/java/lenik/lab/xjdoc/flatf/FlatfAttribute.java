package lenik.lab.xjdoc.flatf;

import java.util.Map.Entry;

public final class FlatfAttribute
        implements IFlatfAttribute, Entry<String, String> {

    String name;
    String text;

    public FlatfAttribute() {
    }

    public FlatfAttribute(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public String setValue(String value) {
        String old = this.text;
        this.text = value;
        return old;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof FlatfAttribute))
            return false;
        FlatfAttribute o = (FlatfAttribute) obj;
        if (name != o.name)
            if (name == null || !name.equals(o.name))
                return false;
        if (text != o.text)
            if (text == null || !text.equals(o.text))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (name != null)
            hash += name.hashCode();
        if (text != null)
            hash = hash * 17 + text.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return name + " = " + text;
    }

}

package net.bodz.bas.util;

@Deprecated
public class Key {

    private final String name;

    public Key(String name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "#" + name; 
    }

}

package net.bodz.bas.ant.util;

public class NamedParameter
        extends Parameter {

    public String name;

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public String toString() {
        return name + '=' + super.toString();
    }

}

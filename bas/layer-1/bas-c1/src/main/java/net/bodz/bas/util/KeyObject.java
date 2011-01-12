package net.bodz.bas.util;

public class KeyObject {

    private final String name;

    public KeyObject() {
        String typeName = getClass().getSimpleName();
        this.name = typeName + ":" + System.identityHashCode(this);
    }

    public KeyObject(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        return this == object;
    }

    @Override
    public String toString() {
        return getName();
    }

}

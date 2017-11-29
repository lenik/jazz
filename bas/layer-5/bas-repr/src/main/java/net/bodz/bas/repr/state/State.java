package net.bodz.bas.repr.state;

import java.io.Serializable;

import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public class State
        // extends Predef<State, Integer>
        extends XjdocObject
        implements Serializable, Comparable<State> {

    private static final long serialVersionUID = 1L;

    private final int key;
    private final String name;
    private final Class<?> declaringClass;
    private final StateType type;

    State(int key, String name, StateType type, Class<?> declaringClass) {
        if (name == null)
            throw new NullPointerException("name");
        this.key = key;
        this.name = name;

        if (declaringClass == null)
            throw new NullPointerException("declaringClass");
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
        this.declaringClass = declaringClass;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    public StateType getType() {
        return type;
    }

    public String getQName() {
        StringBuilder qName = new StringBuilder();
        qName.append(declaringClass.getName());
        qName.append("::");
        qName.append(name);
        return qName.toString();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(State o) {
        if (o == null)
            return 1;
        if (o == this)
            return 0;
        int cmp = key - o.getKey();
        return cmp;
    }

}

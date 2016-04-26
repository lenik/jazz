package net.bodz.bas.repr.state;

import java.util.zip.CRC32;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public class State
        extends XjdocObject {

    private final int id;
    private final Class<?> declaringClass;
    private final String name;
    private final StateType type;

    public State(int id, Class<?> declaringClass, String name, StateType type) {
        if (declaringClass == null)
            throw new NullPointerException("declaringClass");
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");
        this.id = id;
        this.declaringClass = declaringClass;
        this.name = name;
        this.type = type;
    }

    public State(Class<?> declaringClass, String name, StateType type) {
        if (declaringClass == null)
            throw new NullPointerException("declaringClass");
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");

        this.declaringClass = declaringClass;
        this.name = name;
        this.type = type;

        CRC32 crc32 = new CRC32();
        byte[] bytes = getQName().getBytes(Charsets.UTF8);
        crc32.update(bytes);
        this.id = (int) crc32.getValue();
    }

    public int getId() {
        return id;
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

}

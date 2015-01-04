package net.bodz.bas.repr.state;

import java.util.zip.CRC32;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public class State
        extends XjdocObject {

    private final int id;
    private final String name;
    private final StateType type;

    public State(int id, String name, StateType type) {
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public State(String name, StateType type) {
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");

        CRC32 crc32 = new CRC32();
        byte[] nameBytes = name.getBytes(Charsets.UTF8);
        crc32.update(nameBytes);
        this.id = (int) crc32.getValue();

        this.name = name;
        this.type = type;
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

    @Override
    public String toString() {
        return getName();
    }

}

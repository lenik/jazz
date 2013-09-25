package net.bodz.bas.data.util;

import java.io.Serializable;

public class Crc32
        implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private Crc32Core core;
    private int value;

    public Crc32() {
        this(Crc32Core.getInstance(), 0);
    }

    public Crc32(Crc32Core core, int value) {
        this.core = core;
        this.value = value;
    }

    @Override
    public Crc32 clone() {
        Crc32 copy = new Crc32(core, value);
        return copy;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void _update(int b) {
        value = core._update(value, b);
    }

    public void update(int b) {
        value = core.update(value, b);
    }

    public void update(byte[] buf) {
        value = core.update(value, buf);
    }

    public void update(byte[] buf, int off, int len) {
        value = core.update(value, buf, off, len);
    }

}

package net.bodz.dist.pm;

import java.nio.ByteBuffer;

/**
 * This type of VM share a common format of instructions.
 */
public abstract class CiscVM {

    static final int END = 0;

    public void execute(ByteBuffer instructions) throws VMException {
    }

    protected int readInt(ByteBuffer buf) {
        int opcode = 0;
        int byt;
        do {
            byt = buf.get() & 0xff;
            opcode = (opcode << 7) | (byt & 0x7f);
        } while ((byt & 0x80) != 0);
        return opcode;
    }

    protected Object readObject(ByteBuffer buf) {
        byte type = buf.get();
        if (type == 0)
            return null;
        return null;
    }

    protected abstract void execute(int opcode, Object parameter) throws VMException;

}

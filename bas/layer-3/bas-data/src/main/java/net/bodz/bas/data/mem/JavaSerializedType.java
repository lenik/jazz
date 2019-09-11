package net.bodz.bas.data.mem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializedType
        extends AbstractType {

    @Override
    public Object get(IMemory memory, int offset)
            throws MemoryAccessException {
        MemoryIn min = new MemoryIn(memory, offset, -1);
        try {
            ObjectInputStream in = new ObjectInputStream(min);
            try {
                return in.readObject();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
            throw new MemoryAccessException(e);
        } finally {
            try {
                min.close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        MemoryOut mout = new MemoryOut(memory, offset, -1);
        try {
            ObjectOutputStream out = new ObjectOutputStream(mout);
            try {
                out.writeObject(value);
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
            throw new MemoryAccessException(e);
        } finally {
            try {
                mout.close();
            } catch (IOException e) {
            }
        }
    }
}

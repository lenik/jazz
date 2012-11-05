package net.bodz.bas.mem;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializedType
        extends AbstractType {

    @Override
    public Object get(Memory memory, int offset)
            throws MemoryAccessException {
        try (MemoryInputStream min = new MemoryInputStream(memory, offset, -1);
                ObjectInputStream in = new ObjectInputStream(min)) {
            return in.readObject();
        } catch (Exception e) {
            throw new MemoryAccessException(e);
        }
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        try (MemoryOutputStream mout = new MemoryOutputStream(memory, offset, -1);
                ObjectOutputStream out = new ObjectOutputStream(mout)) {
            out.writeObject(value);
        } catch (Exception e) {
            throw new MemoryAccessException(e);
        }
    }
}

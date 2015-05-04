package net.bodz.bas.data.mem;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializedType
        extends AbstractType {

    @Override
    public Object get(IMemory memory, int offset)
            throws MemoryAccessException {
        try (MemoryIn min = new MemoryIn(memory, offset, -1);
                ObjectInputStream in = new ObjectInputStream(min)) {
            return in.readObject();
        } catch (Exception e) {
            throw new MemoryAccessException(e);
        }
    }

    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        try (MemoryOut mout = new MemoryOut(memory, offset, -1);
                ObjectOutputStream out = new ObjectOutputStream(mout)) {
            out.writeObject(value);
        } catch (Exception e) {
            throw new MemoryAccessException(e);
        }
    }
}

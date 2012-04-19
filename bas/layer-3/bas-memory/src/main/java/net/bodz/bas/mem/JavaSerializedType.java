package net.bodz.bas.mem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializedType
        extends AbstractType {

    @Override
    public Object get(Memory memory, int offset)
            throws AccessException {
        MemoryInputStream min = new MemoryInputStream(memory, offset, -1);
        try {
            ObjectInputStream in = new ObjectInputStream(min);
            return in.readObject();
        } catch (IOException e) {
            throw new AccessException(e);
        } catch (ClassNotFoundException e) {
            throw new AccessException(e);
        }
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws AccessException {
        MemoryOutputStream mout = new MemoryOutputStream(memory, offset, -1);
        try {
            ObjectOutputStream out = new ObjectOutputStream(mout);
            out.writeObject(value);
        } catch (IOException e) {
            throw new AccessException(e);
        }
    }

}

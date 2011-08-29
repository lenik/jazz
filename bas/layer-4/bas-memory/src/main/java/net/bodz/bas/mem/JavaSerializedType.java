package net.bodz.bas.mem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;

public class JavaSerializedType
        extends AbstractType {

    @Override
    public Object get(Memory memory, int offset)
            throws AccessException {
        MemoryInputStream min = new MemoryInputStream(memory, offset, -1);
        try {
            ObjectInputStream in = new ObjectInputStream(min);
            return Jdk7Reflect.readObject(in);
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

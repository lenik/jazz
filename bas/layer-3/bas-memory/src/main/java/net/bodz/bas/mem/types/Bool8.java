package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AbstractType;
import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;

public class Bool8
        extends AbstractType {

    public static final byte TRUE = 1;
    public static final byte FALSE = 0;

    @Override
    public Boolean get(Memory memory, int offset)
            throws AccessException {
        return memory.read(offset) != 0;
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws AccessException {
        if ((Boolean) value)
            memory.write(offset, TRUE);
        else
            memory.write(offset, FALSE);
    }

}

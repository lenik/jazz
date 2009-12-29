package net.bodz.bas.io.serial.simple.impl;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.type.ISimpleSerializer;
import net.bodz.bas.type.SerializeException;

public abstract class AbstractSimpleSerializer
        implements ISimpleSerializer {

    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        s.write(String.valueOf(o));
    }

}

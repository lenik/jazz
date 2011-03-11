package net.bodz.bas.type.srt.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.type.srt.ISimpleSerializer;
import net.bodz.bas.type.srt.SerializeException;

public abstract class AbstractSimpleSerializer
        implements ISimpleSerializer {

    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        s.write(String.valueOf(o));
    }

    protected static String readTill(Reader reader, char term)
            throws IOException {
        StringBuilder buffer = new StringBuilder();
        int c;
        while ((c = reader.read()) >= 0) {
            if (c == term)
                break;
            buffer.append((char) c);
        }
        return buffer.toString();
    }

}

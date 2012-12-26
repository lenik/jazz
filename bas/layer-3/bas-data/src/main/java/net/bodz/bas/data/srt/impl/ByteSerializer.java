package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;

/** b?; */
public class ByteSerializer
        extends AbstractSimpleSerializer {

    @Override
    public Byte unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return Byte.parseByte(text);
    }

}

package net.bodz.bas.srt.impl;

import java.io.IOException;
import java.io.Reader;

/** ii?; */
public class LongSerializer
        extends AbstractSimpleSerializer {

    @Override
    public Long unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return Long.parseLong(text);
    }

}

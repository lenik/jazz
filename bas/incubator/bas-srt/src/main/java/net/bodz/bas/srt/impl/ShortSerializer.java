package net.bodz.bas.srt.impl;

import java.io.IOException;
import java.io.Reader;

/** bb?; */
public class ShortSerializer
        extends AbstractSimpleSerializer {

    @Override
    public Short unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return Short.parseShort(text);
    }

}

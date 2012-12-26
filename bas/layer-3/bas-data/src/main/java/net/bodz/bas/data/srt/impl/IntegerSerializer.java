package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;

/** i?; */
public class IntegerSerializer
        extends AbstractSimpleSerializer {

    @Override
    public Integer unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return Integer.parseInt(text);
    }

}

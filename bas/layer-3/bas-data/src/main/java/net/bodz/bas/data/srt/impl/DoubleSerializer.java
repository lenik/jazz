package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;

/** ff?; */
public class DoubleSerializer
        extends AbstractSimpleSerializer {

    @Override
    public Double unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return Double.parseDouble(text);
    }

}

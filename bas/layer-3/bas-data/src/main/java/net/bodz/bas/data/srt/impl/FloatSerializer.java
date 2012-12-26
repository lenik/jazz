package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;

/** f?; */
public class FloatSerializer
        extends AbstractSimpleSerializer {

    @Override
    public Float unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return Float.parseFloat(text);
    }

}

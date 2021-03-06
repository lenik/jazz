package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/** B?; */
public class BooleanSerializer
        extends AbstractSimpleSerializer {

    @Override
    public void serialize(Writer w, Object o)
            throws IOException {
        if (Boolean.TRUE.equals(o))
            w.write("1");
        else
            w.write("0");
    }

    @Override
    public Boolean unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return "1".equals(text);
    }

}

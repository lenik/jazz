package net.bodz.bas.type.srt.impl;

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
            w.write("1"); //$NON-NLS-1$
        else
            w.write("0"); //$NON-NLS-1$
    }

    @Override
    public Boolean unserialize(Reader s)
            throws IOException {
        String text = readTill(s, ';');
        return "1".equals(text); //$NON-NLS-1$
    }

}

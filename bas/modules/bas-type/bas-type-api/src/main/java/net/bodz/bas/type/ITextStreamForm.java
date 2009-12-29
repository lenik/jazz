package net.bodz.bas.type;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * External SRT
 */
public interface ITextStreamForm {

    void serialize(Writer s, Object o)
            throws IOException, SerializeException;

    Object unserialize(Reader s)
            throws IOException, SerializeException;

}

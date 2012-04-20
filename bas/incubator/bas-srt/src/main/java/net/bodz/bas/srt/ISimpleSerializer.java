package net.bodz.bas.srt;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * External SRT
 */
public interface ISimpleSerializer {

    void serialize(Writer s, Object o)
            throws IOException, SerializeException;

    Object unserialize(Reader s)
            throws IOException, SerializeException;

}

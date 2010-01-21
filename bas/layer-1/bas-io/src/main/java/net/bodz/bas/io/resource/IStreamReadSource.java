package net.bodz.bas.io.resource;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.Reader;

import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.lookahead.LAReader;

public interface IStreamReadSource
        extends ISimpleStreamReadSource {

    /**
     * @return non-<code>null</code> value.
     */
    InputStream newInputStream()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    DataInput newDataInput()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    ObjectInput newObjectInput()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    Reader newReader()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    BufferedReader newBufferedReader()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    LineReader newLineReader()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    LAReader newLAReader()
            throws IOException;

}

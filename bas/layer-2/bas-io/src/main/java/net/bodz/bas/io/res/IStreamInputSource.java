package net.bodz.bas.io.res;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.io.impl.LAReader;
import net.bodz.bas.sugar.IToolable;

public interface IStreamInputSource
        extends ISimpleStreamInputSource, IToolable {

    /**
     * Set the charset of the file.
     * 
     * @param charset
     *            Non-<code>null</code> {@link Charset} to be used.
     */
    @Override
    void setCharset(Charset charset);

    /**
     * Set the charset of the file.
     * 
     * @param charset
     *            Non-<code>null</code> charset name to be used.
     * @throws IllegalCharsetNameException
     *             If the charset name is illegal.
     */
    void setCharset(String charsetName);

    /**
     * @return non-<code>null</code> value.
     */
    InputStream newInputStream(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    DataInput newDataInput(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    ObjectInput newObjectInput(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    Reader newReader(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    BufferedReader newBufferedReader(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    LineReader newLineReader(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    LAReader newLAReader(OpenOption... options)
            throws IOException;

}

package net.bodz.bas.fs;

import java.io.IOException;
import java.nio.charset.Charset;

public interface IFile
        extends IEntry, Cloneable {

    /**
     * @return <code>null</code> if {@link Charset} is unknown.
     */
    Charset getCharset();

    /**
     * Override default charset.
     * 
     * @see #newReader(Charset)
     * @see #newWriter(Charset, boolean)
     */
    void setCharset(Charset charset);

    /**
     * @return <code>null</code> if MIME-Type is unknown.
     */
    String getMIMEType();

    /**
     * @return <code>null</code> is length is unknown.
     */
    Long getLength();

    boolean isText()
            throws IOException;

    boolean isBinary()
            throws IOException;

    boolean isDeleteOnExit();

    void setDeleteOnExit(boolean deleteOnExit);

    /**
     * @throws IOException
     *             if the file is existed and can't be deleted.
     * @return <code>false</code> if the file wasn't existed.
     */
    boolean delete()
            throws IOException;

    AbstractReadToolkit forRead();

    AbstractWriteToolkit forWrite();

    AbstractLoadToolkit forLoad();

    AbstractDumpToolkit forDump();

}

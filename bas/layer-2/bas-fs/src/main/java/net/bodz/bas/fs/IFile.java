package net.bodz.bas.fs;

import java.io.IOException;
import java.nio.charset.Charset;

public interface IFile
        extends IFsEntry, Cloneable {

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
     * @return <code>null</code> is length is unknown.
     */
    Long getLength();

    boolean isExecutable();

    boolean isDeleteOnExit();

    void setDeleteOnExit(boolean deleteOnExit);

    /**
     * @throws IOException
     *             if the file is existed and can't be deleted.
     * @return <code>false</code> if the file wasn't existed.
     */
    boolean delete()
            throws IOException;

    /**
     * @throw {@link UnsupportedOperationException} If read functionality isn't supported.
     * 
     */
    IReadToolkit forRead();

    /**
     * @throw {@link UnsupportedOperationException} If write functionality isn't supported.
     */
    IWriteToolkit forWrite();

    /**
     * @throw {@link UnsupportedOperationException} If load functionality isn't supported.
     */
    ILoadToolkit forLoad();

    /**
     * @throw {@link UnsupportedOperationException} If dump functionality isn't supported.
     */
    IDumpToolkit forDump();

    /**
     * @throw {@link UnsupportedOperationException} If probe functionality isn't supported.
     */
    IProbeToolkit forProbe(boolean heuristic);

}

package net.bodz.bas.fs;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.fs.preparation.IProbePreparation;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.preparation.IFormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IParseLoadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;

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

    void setCharset(String charsetName);

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

    IStreamInputSource toStreamInputSource();

    IStreamOutputTarget toStreamOutputTarget();

    /**
     * @throw {@link UnsupportedOperationException} If read functionality isn't supported.
     * 
     */
    IStreamReadPreparation forRead();

    /**
     * @throw {@link UnsupportedOperationException} If write functionality isn't supported.
     */
    IStreamWritePreparation forWrite();

    /**
     * @throw {@link UnsupportedOperationException} If load functionality isn't supported.
     */
    IParseLoadPreparation forLoad();

    /**
     * @throw {@link UnsupportedOperationException} If dump functionality isn't supported.
     */
    IFormatDumpPreparation forDump();

    /**
     * @throw {@link UnsupportedOperationException} If probe functionality isn't supported.
     */
    IProbePreparation forProbe(boolean heuristic);

}

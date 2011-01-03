package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.preparation.IFormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IParseLoadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.vfs.preparation.IProbePreparation;

public interface IFsFileEntry
        extends IFsEntry {

    @Override
    IFsFileEntry clone();

    /**
     * @return <code>null</code> if {@link Charset} is unknown.
     */
    Charset getPreferredCharset();

    /**
     * Override default charset.
     * 
     * @see #newReader(Charset)
     * @see #newWriter(Charset, boolean)
     */
    void setPreferredCharset(Charset charset);

    void setPreferredCharset(String charsetName);

    long getLength();

    /**
     * For stream files, the value returned by {@link #getLength()} means the approx file size.
     */
    boolean isStream();

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

    IStreamInputSource asSource();

    IStreamOutputTarget asTarget();

    /**
     * @throws UnsupportedOperationException
     *             If read functionality isn't supported.
     * 
     */
    IStreamReadPreparation forRead();

    /**
     * @throws UnsupportedOperationException
     *             If write functionality isn't supported.
     */
    IStreamWritePreparation forWrite();

    /**
     * @throws UnsupportedOperationException
     *             If load functionality isn't supported.
     */
    IParseLoadPreparation forLoad();

    /**
     * @throws UnsupportedOperationException
     *             If dump functionality isn't supported.
     */
    IFormatDumpPreparation forDump();

    /**
     * @throws UnsupportedOperationException
     *             If probe functionality isn't supported.
     */
    IProbePreparation forProbe(boolean heuristic);

}

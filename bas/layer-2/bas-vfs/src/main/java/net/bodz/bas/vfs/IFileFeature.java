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

public interface IFileFeature
        extends IFsEntry {

    @Override
    IFileFeature clone();

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
     * For stream files, the value returned by {@link #getLength()} is not accuraccy and maybe
     * <code>0</code>.
     */
    boolean isStream();

    /**
     * @throws IOException
     *             if the file is existed and can't be deleted.
     * @return <code>false</code> if the file wasn't existed.
     */
    boolean delete()
            throws IOException;

    IStreamInputSource toSource();

    IStreamOutputTarget toTarget();

    IStreamReadPreparation forRead();

    IStreamWritePreparation forWrite();

    IParseLoadPreparation forLoad();

    IFormatDumpPreparation forDump();

    IProbePreparation forProbe(boolean heuristic);

}

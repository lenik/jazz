package net.bodz.bas.vfs;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.preparation.IFormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IParseLoadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;

public interface IFsBlob
        extends IFsEntry {

    /**
     * Clone the status, but no blob data.
     * 
     * @return The cloned object with a separate status copy.
     */
    @Override
    IFsBlob clone();

    /**
     * Get the preferred charset which will be used to access the blob in text mode.
     * 
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

    /**
     * Override default charset.
     * 
     * @see #newReader(Charset)
     * @see #newWriter(Charset, boolean)
     */
    void setPreferredCharset(String charsetName);

    boolean isExecutable();

    /**
     * Get the file length of a non-stream fs blob.
     * 
     * If this is a stream blob, the returned length is undetermined.
     * 
     * @return File length in bytes.
     */
    long getLength();

    /**
     * For stream files, the value returned by {@link #getLength()} is not accuraccy and maybe
     * <code>0</code>.
     */
    boolean isStream();

    /**
     * @return <code>null</code> If this isn't a file blob.
     */
    IStreamInputSource toSource();

    /**
     * @return <code>null</code> If this isn't a file blob.
     */
    IStreamOutputTarget toTarget();

    /**
     * @return <code>null</code> If this isn't a file blob.
     */
    IStreamReadPreparation forRead();

    /**
     * @return <code>null</code> If this isn't a file blob.
     */
    IStreamWritePreparation forWrite();

    /**
     * @return <code>null</code> If this isn't a file blob.
     */
    IParseLoadPreparation forLoad();

    /**
     * @return <code>null</code> If this isn't a file blob.
     */
    IFormatDumpPreparation forDump();

}

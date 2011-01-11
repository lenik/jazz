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
     * Get the file length.
     * <p>
     * <i> If you override this method, you should override the {@link #length()} at the same time.
     * </i>
     * 
     * @return <code>null</code> if the file length is unknown, or the file length in bytes, .
     */
    Long getLength();

    /**
     * Get the file length.
     * <p>
     * <i> If you override this method, you should override the {@link #length()} at the same time.
     * </i>
     * 
     * @return <code>0</code> if the file length is unknown, or the file length in bytes.
     */
    long length();

    /**
     * Return whether this file is random seekable.
     * 
     * @return <code>true</code> If this file is ramdom seekable.
     */
    boolean isSeekable();

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

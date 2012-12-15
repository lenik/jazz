package net.bodz.bas.vfs;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamResourceWrapper;

public interface IFsBlob
        extends IFsEntry, IStreamResourceWrapper {

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

    boolean setExecutable(boolean executable);

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

}

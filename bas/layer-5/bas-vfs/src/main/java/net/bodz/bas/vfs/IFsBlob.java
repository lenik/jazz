package net.bodz.bas.vfs;

import java.io.IOException;
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
     * Trim the file to the length, or extend the file, the extension part may be filled with
     * unknown bytes.
     * 
     * @param newLength
     *            New length to be set. The length should be >= <code>0</code>.
     * @return <code>false</code> If the given file object does not denote an existing, writable
     *         regular file and a new regular file of that name cannot be created, or if some other
     *         error occurs while opening or creating the file.
     * @exception IOException
     *                If an I/O error occurs
     */
    boolean setLength(long newLength)
            throws IOException;

    /**
     * Create a non-existed file, or update the last modification time of the file represented by
     * this object.
     * 
     * @param update
     * @return <code>true</code> if the blob is updated, or created if it's not existed. Otherwise,
     *         if the blob entry is read-only, or it's a dir-only entry, returns <code>false</code>.
     */
    boolean touch(boolean updateLastModifiedTime)
            throws IOException;

    /**
     * Return whether this file is random seekable.
     * 
     * @return <code>true</code> If this file is ramdom seekable.
     */
    boolean isSeekable();

}

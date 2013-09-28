package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.res.IRandomResourceWrapper;

public interface IFsBlob
        extends IFsObject, IRandomResourceWrapper {

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

    /**
     * Get the file length.
     * 
     * @return File length in bytes, <code>0</code> if the file isn't existed. If the length of the
     *         file can't be known, <code>-1</code> is returned.
     */
    long getLength()
            throws IOException;

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
     * Create a non-existed file, and update the last modification time of the file represented by
     * this object.
     * 
     * @param touch
     *            Update the last modification time if the file is already existed.
     * @return <code>true</code> if the blob is updated, or created if it's not existed. Otherwise,
     *         if the blob entry is read-only, or it's a dir-only entry, returns <code>false</code>.
     */
    boolean mkblob(boolean touch)
            throws IOException;

}

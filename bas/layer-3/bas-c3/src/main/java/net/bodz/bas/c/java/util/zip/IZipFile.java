package net.bodz.bas.c.java.util.zip;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import net.bodz.bas.t.iterator.Iterables;

public interface IZipFile
        extends Closeable {

    /**
     * Returns the zip file comment, or null if none.
     * 
     * @return the comment string for the zip file, or null if none
     * @throws IllegalStateException
     *             if the zip file has been closed
     */
    String getComment();

    /**
     * Returns the zip file entry for the specified name, or null if not found.
     * 
     * @param name
     *            the name of the entry
     * @return the zip file entry, or null if not found
     * @throws IllegalStateException
     *             if the zip file has been closed
     */
    ZipEntry getEntry(String name);

    /**
     * Returns an input stream for reading the contents of the specified zip file entry.
     * 
     * <p>
     * Closing this ZIP file will, in turn, close all input streams that have been returned by
     * invocations of this method.
     * 
     * @param entry
     *            the zip file entry
     * @return the input stream for reading the contents of the specified zip file entry.
     * @throws ZipException
     *             if a ZIP format error has occurred
     * @throws IOException
     *             if an I/O error has occurred
     * @throws IllegalStateException
     *             if the zip file has been closed
     */
    InputStream getInputStream(ZipEntry entry)
            throws IOException;

    /**
     * Returns an enumeration of the ZIP file entries.
     * 
     * @return an enumeration of the ZIP file entries
     * @throws IllegalStateException
     *             if the zip file has been closed
     */
    Iterable<? extends ZipEntry> entries();

    /**
     * Returns the number of entries in the ZIP file.
     * 
     * @return the number of entries in the ZIP file
     * @throws IllegalStateException
     *             if the zip file has been closed
     */
    int size();

    /**
     * Closes the ZIP file.
     * <p>
     * Closing this ZIP file will close all of the input streams previously returned by invocations
     * of the {@link #getInputStream getInputStream} method.
     * 
     * @throws IOException
     *             if an I/O error has occurred
     */
    void close()
            throws IOException;

    class fn {
        public static IZipFile impl(final ZipFile zipFile) {
            return new IZipFile() {

                @Override
                public String getComment() {
                    return zipFile.getComment();
                }

                @Override
                public ZipEntry getEntry(String name) {
                    return zipFile.getEntry(name);
                }

                @Override
                public InputStream getInputStream(ZipEntry entry)
                        throws IOException {
                    return zipFile.getInputStream(entry);
                }

                @Override
                public Iterable<? extends ZipEntry> entries() {
                    return Iterables.otp(zipFile.entries());
                }

                @Override
                public int size() {
                    return zipFile.size();
                }

                @Override
                public void close()
                        throws IOException {
                    zipFile.close();
                }

            };
        }
    }

}

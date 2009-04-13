package net.bodz.bas.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

public interface ResLink {

    /**
     * @return <code>null</code> if there isn't a corresponding {@link URL}.
     */
    URL getURL();

    /**
     * @return <code>null</code> if there isn't a corresponding {@link File}.
     */
    File getFile();

    /**
     * @return <code>null</code> if unknown.
     */
    Boolean exists();

    /**
     * @throws UnsupportedOperationException
     *             If target resource can't be opened as {@link InputStream}.
     */
    InputStream openInputStream() throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If target resource can't be opened as {@link OutputStream},
     *             or if target resource doesn't support append mode.
     */
    OutputStream openOutputStream(boolean append) throws IOException;

    /**
     * @param encoding
     *            may be <code>null</code> if target resource has a default
     *            encoding.
     * @throws UnsupportedOperationException
     *             If target resource can't be opened as {@link Reader},
     */
    Reader openReader(String encoding) throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If target resource can't be opened as {@link Writer},
     */
    Writer openWriter(boolean append, String encoding) throws IOException;

}

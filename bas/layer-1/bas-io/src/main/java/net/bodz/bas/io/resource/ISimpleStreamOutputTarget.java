package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

public interface ISimpleStreamOutputTarget {

    Charset getCharset();

    /**
     * @throws NullPointerException
     *             If <code>charset</code> is <code>null</code>.
     */
    void setCharset(Charset charset);

    /**
     * @return non-<code>null</code> value.
     */
    IByteOut newByteOut()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    ICharOut newCharOut()
            throws IOException;

}

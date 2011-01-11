package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;

public interface ISimpleStreamInputSource
        extends Cloneable {

    ISimpleStreamInputSource clone();

    Charset getCharset();

    /**
     * @throws NullPointerException
     *             If <code>charset</code> is <code>null</code>.
     */
    void setCharset(Charset charset);

    /**
     * @return non-<code>null</code> value.
     */
    IByteIn newByteIn()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    ICharIn newCharIn()
            throws IOException;

}

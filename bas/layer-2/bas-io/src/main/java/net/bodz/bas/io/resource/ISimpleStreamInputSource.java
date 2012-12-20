package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;

public interface ISimpleStreamInputSource {

    Charset getCharset();

    /**
     * @throws NullPointerException
     *             If <code>charset</code> is <code>null</code>.
     */
    void setCharset(Charset charset);

    /**
     * @return non-<code>null</code> value.
     */
    IByteIn newByteIn(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    ICharIn newCharIn(OpenOption... options)
            throws IOException;

}

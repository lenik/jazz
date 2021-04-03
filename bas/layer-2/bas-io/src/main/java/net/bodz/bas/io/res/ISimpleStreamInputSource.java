package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;

public interface ISimpleStreamInputSource
        extends
            IOpenResourceSource {

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

    boolean isCharInPreferred();

}

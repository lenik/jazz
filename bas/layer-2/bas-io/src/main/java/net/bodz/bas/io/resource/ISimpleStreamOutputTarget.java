package net.bodz.bas.io.resource;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

/**
 * 相当于一个只写的File对象，其打开操作可定制。
 */
public interface ISimpleStreamOutputTarget
        extends Cloneable {

    ISimpleStreamOutputTarget clone();

    boolean isAppendMode();

    void setAppendMode(boolean appendMode);

    Charset getCharset();

    /**
     * @throws NullPointerException
     *             If <code>charset</code> is <code>null</code>.
     * @return this self.
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
